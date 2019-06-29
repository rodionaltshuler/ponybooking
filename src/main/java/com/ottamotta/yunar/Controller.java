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


}
