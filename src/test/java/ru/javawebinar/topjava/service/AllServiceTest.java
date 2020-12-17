package ru.javawebinar.topjava.service;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        MealServiceDatajpaTest.class,
        MealServiceJdbcTest.class,
        MealServiceJpaTest.class,
        UserServiceDatajpaTest.class,
        UserServiceJdbcTest.class,
        UserServiceJpaTest.class,
})
public class AllServiceTest {
}
