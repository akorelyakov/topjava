package ru.javawebinar.topjava.service;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import ru.javawebinar.topjava.service.datajpa.MealServiceDatajpaTest;
import ru.javawebinar.topjava.service.datajpa.UserServiceDatajpaTest;
import ru.javawebinar.topjava.service.jdbc.MealServiceJdbcTest;
import ru.javawebinar.topjava.service.jdbc.UserServiceJdbcTest;
import ru.javawebinar.topjava.service.jpa.MealServiceJpaTest;
import ru.javawebinar.topjava.service.jpa.UserServiceJpaTest;

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
