package ru.javawebinar.topjava.repository.inmemory;

import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.Util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static ru.javawebinar.topjava.util.MealsUtil.ADMIN_ID;
import static ru.javawebinar.topjava.util.MealsUtil.USER_ID;

@Repository
public class InMemoryMealRepository implements MealRepository {
    private final Map<Integer, Map<Integer, Meal>> repository = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(0);

    {
        save(USER_ID, new Meal(LocalDateTime.of(2020, Month.NOVEMBER, 4, 14, 0), "Юзер ланч", 510));
        save(USER_ID, new Meal(LocalDateTime.of(2020, Month.NOVEMBER, 4, 21, 0), "Юзер ужин", 1500));
        save(USER_ID, new Meal(LocalDateTime.of(2020, Month.NOVEMBER, 6, 21, 0), "Юзер ужин", 1500));
        save(USER_ID, new Meal(LocalDateTime.of(2020, Month.NOVEMBER, 6, 21, 0), "Юзер ужин", 1500));
        save(ADMIN_ID, new Meal(LocalDateTime.of(2020, Month.NOVEMBER, 8, 21, 0), "Админ ужин", 100));
        save(ADMIN_ID, new Meal(LocalDateTime.of(2020, Month.NOVEMBER, 8, 21, 0), "Админ ужин", 1500));
    }

    @Override
    public Meal save(int userId, Meal meal) {
        Map<Integer, Meal> meals = repository.computeIfAbsent(userId, ConcurrentHashMap::new);
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            meals.put(meal.getId(), meal);
            return meal;
        }
        return meals.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    @Override
    public boolean delete(int userId, int id) {
        Map<Integer, Meal> meals = repository.getOrDefault(userId, null);
        return meals != null && meals.remove(id) != null;
    }

    @Override
    public Meal get(int userId, int id) {
        Map<Integer, Meal> meals = repository.getOrDefault(userId, null);
        return meals == null ? null : meals.get(id);
    }

    @Override
    public List<Meal> getAll(int userId) {
        return filterByPredicate(userId, meal -> true);
    }

    @Override
    public List<Meal> getFilteredListByDate(int userId, LocalDate startDate, LocalDate endDate) {
        return filterByPredicate(userId, meal -> Util.isBetweenHalfOpen(meal.getDate(), startDate, endDate));
    }

    private List<Meal> filterByPredicate(int userId, Predicate<Meal> filter) {
        Map<Integer, Meal> meals = repository.getOrDefault(userId, null);
        return meals == null ? Collections.emptyList() :
                meals.values().stream()
                .filter(filter)
                .sorted(Comparator.comparing(Meal::getDateTime).reversed())
                .collect(Collectors.toList());
    }
}
