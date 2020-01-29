package com.bss.book.restapi.controller;

import com.bss.book.domain.dto.BookCategoryParam;
import com.bss.book.domain.dto.BookCategoryWithChildrenItem;
import com.bss.book.domain.dto.CommonResult;
import com.bss.book.domain.model.BookCategory;
import com.bss.book.restapi.service.BookCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Created by QAQ on 2019/4/23
 */
@RestController
@RequestMapping("/category")
public class BookCategoryController {
    @Autowired
    BookCategoryService bookCategoryService;

    @GetMapping("/listWithChildren")
    public CompletableFuture<String> listWithChildren(){
        return CompletableFuture.supplyAsync(() -> {
            List<BookCategoryWithChildrenItem> list = bookCategoryService.listWithChildren();
            return new CommonResult().success(list);
        });
    }

    @GetMapping("/list")
    public CompletableFuture<String> listAll(@RequestParam("pageNum") Integer pageNum,
                                             @RequestParam("pageSize") Integer pageSize){
        return CompletableFuture.supplyAsync(() -> {
           List<BookCategory> list = bookCategoryService.listAll(pageNum,pageSize);
           return new CommonResult().pageSuccess(list);
        });
    }


    @PostMapping
    public CompletableFuture<String> create(@RequestBody BookCategoryParam param){
        return CompletableFuture.supplyAsync(() -> {
           int count = bookCategoryService.create(param);

           if (count > 0) {
               return new CommonResult().success(count);
           }else {
               return new CommonResult().failed();
           }
        });
    }

    @DeleteMapping("/{id}")
    public CompletableFuture<String> delete(@PathVariable Integer id){
        return CompletableFuture.supplyAsync(() -> {
           int count = bookCategoryService.deleteById(id);
           return new CommonResult().success(count);
        });
    }

    @GetMapping("/{id}")
    public CompletableFuture<String> getById(@PathVariable Integer id){
        return CompletableFuture.supplyAsync(() -> {
            BookCategory bookCategory = bookCategoryService.getById(id);
           return new CommonResult().success(bookCategory);
        });
    }

    @PutMapping("/{id}")
    public CompletableFuture<String> update(@PathVariable Integer id,
                                            @RequestBody BookCategoryParam param){
        return CompletableFuture.supplyAsync(() -> {
            int count = bookCategoryService.update(id,param);
            if (count > 0) {
                return new CommonResult().success(count);
            }else {
                return new CommonResult().failed();
            }
        });
    }
}
