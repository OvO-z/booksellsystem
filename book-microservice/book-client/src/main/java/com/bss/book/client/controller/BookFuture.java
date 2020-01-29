package com.bss.book.client.controller;

import com.bss.book.client.service.BookClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@Configuration
@RequestMapping("/book")
public class BookFuture {
    @Autowired
    private BookClient bookClient;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public CompletableFuture<String> findById(@PathVariable Long id){
        return CompletableFuture.supplyAsync(() -> bookClient.findById(id));
    }

}
