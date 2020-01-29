package com.bss.book.client.service;

import com.bss.book.client.fallback.BookFallback;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "bookapi", fallback = BookFallback.class)
public interface BookClient {

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    String findById(@RequestParam("id") Long id);

    @RequestMapping(method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String create();

//    @RequestMapping(value = "/category/list", method = RequestMethod.GET)
//    String
}
