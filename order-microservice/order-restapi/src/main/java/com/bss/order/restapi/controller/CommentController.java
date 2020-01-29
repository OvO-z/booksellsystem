package com.bss.order.restapi.controller;

import com.bss.order.domain.dto.CommonResult;
import com.bss.order.object.CommentParam;
import com.bss.order.object.model.Comment;
import com.bss.order.restapi.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by QAQ on 2019/6/1
 */

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    CommentService commentService;

    @GetMapping("/list")
    public Object getList(CommentParam param, @RequestParam("pageNum") Integer pageNum,
                          @RequestParam("pageSize") Integer pageSize){
        List<Comment> comments = new ArrayList<>();
        if (pageNum != null&& pageSize != null) {
            comments = commentService.getPageList(param,pageNum,pageSize);
            return new CommonResult().pageSuccess(comments);
        }else {
            comments = commentService.getList(param);
            return new CommonResult().success(comments);
        }
    }

    @PostMapping
    public Object create(@RequestBody CommentParam param){
        Comment comment = commentService.create(param);
        return new CommonResult().success(comment);
    }

    @PutMapping("/{id}")
    public Object update(@PathVariable("id")Long id, @RequestBody CommentParam param){
        Comment comment = commentService.update(id,param);
        return new CommonResult().success(comment);
    }
}
