package com.ottamotta.yunar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Stream;

@RestController
public class Controller {

    private AppRepository repository;

    @Autowired
    public Controller(AppRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/save")
    public Entity save(@RequestBody Entity value) {
        return repository.save(value);
    }

    @GetMapping("/")
    public Stream<Dto> get(@RequestParam("from") long timestampFrom, @RequestParam("to") long timestampTo) {
        return repository.findAllByTimestampBetweenOrderByTimestamp(timestampFrom, timestampTo).stream()
                .map(Dto::new);
    }

}
