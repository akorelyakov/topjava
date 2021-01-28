package ru.javawebinar.topjava.web.meal;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealTo;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping(value = MealRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class MealRestController extends AbstractMealController {

    public static final String REST_URL = "/rest/meals";

    @Override
    @GetMapping
    public List<MealTo> getAll() {
        return super.getAll();
    }

    @Override
    @GetMapping("/{id}")
    public Meal get(@PathVariable int id) {
        return super.get(id);
    }

    // указываем, что метод принимает объект в формате JSON (consumes = потребляет)
    // и что meal приходит в теле запроса через @RequestBody
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Meal> createWithLocation(@RequestBody Meal meal) {
        Meal created =  super.create(meal);
        //создаем URI нового ресурса, который принято возвращать
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        // стандартный билдер, который также устанавливает код ответа 201 и добавляет URI в заголовок
        // и добавляет в body создаваемую еду
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        super.delete(id);
    }

    @Override
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody Meal meal, @PathVariable int id) {
        super.update(meal, id);
    }

    @Override
    @GetMapping("/filter")
    // чтобы не было конфликта с getAll добавляем в маппинг /filter и параметры берем из запроса
    // с помощью @RequestParam
    public List<MealTo> getBetween(@RequestParam(value = "startdate", required = false)
                                   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                           LocalDate startDate,
                                   @RequestParam(value = "starttime", required = false)
                                   @DateTimeFormat(pattern = "HH:MM")
                                           LocalTime startTime,
                                   @RequestParam(value = "enddate", required = false)
                                   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                           LocalDate endDate,
                                   @RequestParam(value = "endtime", required = false)
                                   @DateTimeFormat(pattern = "HH:MM")
                                           LocalTime endTime) {
        return super.getBetween(startDate, startTime, endDate, endTime);
    }
}