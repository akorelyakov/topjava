package ru.javawebinar.topjava.repository.inmemory;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class InMemoryMealRepository implements MealRepository {
    private Map<Integer, Meal> meals = new ConcurrentHashMap<>();
    private final Map<Integer, Map<Integer, Meal>> repository = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(0);

    public InMemoryMealRepository() {
        for (Meal meal : MealsUtil.meals) {
            save(MealsUtil.USER_ID, meal);
        }
    }

    @Override
    public Meal save(int userId, Meal meal) {
        meals = repository.get(userId);
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            meals.put(meal.getId(), meal);
            repository.put(userId, meals);
            return meal;
        }
        Meal newMeal = meals.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
        repository.put(userId, meals);
        return newMeal;
    }

    @Override
    public boolean delete(int userId, int id) {
        meals = repository.get(userId);
        boolean isDeleted = meals.remove(id) != null;
        repository.put(userId, meals);
        return isDeleted;
    }

    @Override
    public Meal get(int userId, int id) {
        meals = repository.get(userId);
        return meals.get(id);
    }

    @Override
    public List<Meal> getAll(int userId) {
        meals = repository.get(userId);
        return meals.values()
                .stream()
                .sorted(Comparator.comparing(Meal::getDate).reversed())
                .collect(Collectors.toList());
    }
}
