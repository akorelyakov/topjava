package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;

import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.*;
import static ru.javawebinar.topjava.web.SecurityUtil.authUserId;

public class MealRestController {
    protected final Logger log = LoggerFactory.getLogger(getClass());
    private MealService service;

    public List<Meal> getAll(int userId) {
        log.info("getAll");
        return service.getAll(authUserId());
    }

    public Meal get(int userId, int id) {
        log.info("get {}", id);
        return service.get(authUserId(), id);
    }

    public Meal create(int userId, Meal meal) {
        log.info("create {}", meal);
        checkNew(meal);
        return service.create(authUserId(), meal);
    }

    public void delete(int userId, int id) {
        log.info("delete {}", id);
        service.delete(authUserId(), id);
    }

    public void update(int userId, Meal meal, int id) {
        log.info("update {} with id={}", meal, id);
        assureIdConsistent(meal, id);
        service.update(authUserId(), meal);
    }
}