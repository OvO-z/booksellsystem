package com.bss.book.restapi.controller;

import com.bss.book.domain.dto.BookParam;
import com.bss.book.domain.dto.BookQueryParam;
import com.bss.book.domain.dto.CommonResult;
import com.bss.book.domain.model.Book;
import com.bss.book.restapi.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Created by QAQ on 2019/4/28
 */
@RestController
@RequestMapping("/")
public class BookController {

    @Autowired
    BookService bookService;


    @PostMapping
    public CompletableFuture<String> create(@RequestBody BookParam param){
        return CompletableFuture.supplyAsync(() -> {
            Long id = bookService.create(param);
            return new CommonResult().success(id);
        });
    }

    @PutMapping("/{isbn}")
    public CompletableFuture<String> update(@PathVariable Long isbn,
                                            @RequestBody BookParam param){
        return CompletableFuture.supplyAsync(() -> {
            Long count = bookService.update(isbn,param);
            if (count != null) {
                return new CommonResult().success(count);
            }else {
                return new CommonResult().failed();
            }
        });
    }

    @GetMapping
    public CompletableFuture<String> list(BookQueryParam param, @RequestParam("pageNum") Integer pageNum,
                                          @RequestParam("pageSize") Integer pageSize){
        return CompletableFuture.supplyAsync(() -> {
            List<Book> list = bookService.listAll(param,pageNum,pageSize);
            return new CommonResult().pageSuccess(list);
        });
    }

    @GetMapping("/{isbn}")
    public CompletableFuture<String> get(@PathVariable("isbn") Long isbn){
        return CompletableFuture.supplyAsync(() -> {
            Book book = bookService.getById(isbn);
            return new CommonResult().success(book);
        });
    }

    @DeleteMapping("/{isbn}")
    public CompletableFuture<String> delete(@PathVariable("isbn") Long isbn){
        return CompletableFuture.supplyAsync(() -> {
            Long result = bookService.deleteById(isbn);
            return new CommonResult().success(result);
        });
    }
}
