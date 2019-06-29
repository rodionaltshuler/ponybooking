package com.ottamotta.yunar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {

    private PonyService ponyService;

    @Autowired
    public Controller(PonyService ponyService) {
        this.ponyService = ponyService;
    }

    @GetMapping("/{name}")
    public Pony getStatus(@PathVariable("name") String name) {
        return ponyService.getStatus(name);
    }

    @PostMapping("/book/{name}")
    public Pony bookPony(@PathVariable("name") String name) {
        return ponyService.bookPony(name);
    }

    @PostMapping("/return/{name}")
    public Pony returnPony(@PathVariable("name") String name) {
        return ponyService.returnPony(name);
    }

}
