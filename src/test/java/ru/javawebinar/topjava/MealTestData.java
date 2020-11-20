package ru.javawebinar.topjava;

import org.slf4j.bridge.SLF4JBridgeHandler;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {

    public static final int USER_MEAL_1_ID = START_SEQ + 2;
    public static final int USER_MEAL_2_ID = START_SEQ + 3;
    public static final int USER_MEAL_3_ID = START_SEQ + 4;
    public static final int ADMIN_MEAL_1_ID = START_SEQ + 5;
    public static final int ADMIN_MEAL_2_ID = START_SEQ + 6;
    public static final int ADMIN_MEAL_3_ID = START_SEQ + 7;
    public static final int MEAL_NOT_FOUND_ID = 11;
    public static final Meal userMeal1 = new Meal(USER_MEAL_1_ID, LocalDateTime.parse("2020-11-18T14:53:32"),
            "user meal 1000", 1000);
    public static final Meal userMeal2 = new Meal(USER_MEAL_2_ID, LocalDateTime.parse("2020-11-18T15:57:32"),
            "user meal 1100", 1100);
    public static final Meal userMeal3 = new Meal(USER_MEAL_3_ID, LocalDateTime.parse("2020-11-17T14:52:32"),
            "user meal 800", 800);
    public static final Meal adminMeal1 = new Meal(ADMIN_MEAL_1_ID, LocalDateTime.parse("2020-11-18T13:53:32"),
            "admin meal 1000", 1000);
    public static final Meal adminMeal2 = new Meal(ADMIN_MEAL_2_ID, LocalDateTime.parse("2020-11-18T15:53:32"),
            "admin meal 1200", 1200);
    public static final Meal adminMeal3 = new Meal(ADMIN_MEAL_3_ID, LocalDateTime.parse("2020-11-17T14:54:32"),
            "admin meal 800", 800);
    public static final List<Meal> userMeals = Arrays.asList(userMeal2, userMeal1, userMeal3);

    static {
        SLF4JBridgeHandler.install();
    }

    public static Meal getUpdated() {
        Meal updated = new Meal(userMeal1);
        updated.setDescription("Updated Description");
        updated.setCalories(999);
        return updated;
    }

    public static Meal getNew() {
        return new Meal(null, LocalDateTime.now(), "new meal description", 1999);
    }
}
