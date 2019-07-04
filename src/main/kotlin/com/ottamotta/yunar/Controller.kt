package com.ottamotta.yunar

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class Controller @Autowired
constructor(private val ponyService: PonyService) {

    @GetMapping("/{name}")
    fun getStatus(@PathVariable("name") name: String) =
         ponyService.getStatus(name)

    @PostMapping("/book/{name}")
    fun bookPony(@PathVariable("name") name: String) =
        ponyService.bookPony(name)

    @PostMapping("/return/{name}")
    fun returnPony(@PathVariable("name") name: String) =
        ponyService.returnPony(name)

}