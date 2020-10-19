package com.example.BooksMgmt.restservice.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *  @author Vishal Vyavahare
 */
@Api(tags = "Users API", value = "All the User related API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/books")
public class BooksController {

    @GetMapping("/")
    public String getAllBooks() {
        return "Vishal";
    }

    @PostMapping("/")
    public String createUser() {
        return null;
    }

}
