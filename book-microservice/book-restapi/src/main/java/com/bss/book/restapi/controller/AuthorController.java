package com.bss.book.restapi.controller;

import com.bss.book.domain.dto.AuthorParam;
import com.bss.book.domain.dto.CommonResult;
import com.bss.book.domain.model.Author;
import com.bss.book.restapi.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Created by QAQ on 2019/4/24
 */
@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @GetMapping("/list")
    public CompletableFuture<String> listAll(@RequestParam("pageNum") Integer pageNum,
                                             @RequestParam("pageSize") Integer pageSize){
        return CompletableFuture.supplyAsync(() -> {
            List<Author> list = authorService.listAll(pageNum,pageSize);
            return new CommonResult().pageSuccess(list);
        });
    }

    @GetMapping
    public CompletableFuture<String> listAll(){
        return CompletableFuture.supplyAsync(() -> {
           List<Author> list = authorService.getList();
           return new CommonResult().success(list);
        });
    }

    @PostMapping
    public CompletableFuture<String> create(@RequestBody AuthorParam param){
        return CompletableFuture.supplyAsync(() -> {
            String id = authorService.create(param);

            if (id != null) {
                return new CommonResult().success(id);
            }else {
                return new CommonResult().failed();
            }
        });
    }

    @DeleteMapping("/{id}")
    public CompletableFuture<String> delete(@PathVariable String id){
        return CompletableFuture.supplyAsync(() -> {
            String result = authorService.deleteById(id);
            if (result != null) {
                return new CommonResult().success(result);
            }else {
                return new CommonResult().failed();
            }
        });
    }

    @GetMapping("/{id}")
    public CompletableFuture<String> getById(@PathVariable String id){
        return CompletableFuture.supplyAsync(() -> {
            Author author = authorService.getById(id);
            return new CommonResult().success(author);
        });
    }

    @PutMapping("/{id}")
    public CompletableFuture<String> update(@PathVariable String id,
                                            @RequestBody AuthorParam param){
        return CompletableFuture.supplyAsync(() -> {
           param.setId(id);
           String result = authorService.update(param);
            return new CommonResult().success(result);
        });
    }
}
