package com.bss.order.restapi.controller;

import com.bss.order.domain.dto.CommonResult;
import com.bss.order.domain.dto.ReturnApplyParam;
import com.bss.order.domain.dto.ReturnApplyQueryParam;
import com.bss.order.object.model.ReturnApply;
import com.bss.order.restapi.service.ReturnApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by QAQ on 2019/5/31
 */
@RestController
@RequestMapping("/returnApply")
public class ReturnApplyController {

    @Autowired
    ReturnApplyService service;

    @GetMapping("/list")
    public Object getList(ReturnApplyQueryParam param, @RequestParam("pageNum") Integer pageNum,
                          @RequestParam("pageSize") Integer pageSize){
        List<ReturnApply> applies = service.getList(param,pageNum, pageSize);
        return new CommonResult().pageSuccess(applies);
    }

    @PostMapping
    public Object create(@RequestBody ReturnApplyParam param){
        ReturnApply apply = service.create(param);
        return new CommonResult().success(apply);
    }

    @PutMapping("/{id}")
    public Object update(@PathVariable("id") Long id, @RequestBody ReturnApplyParam param){
        ReturnApply apply = service.update(id,param);
        return new CommonResult().success(apply);
    }

    @DeleteMapping("/{id}")
    public Object delete(@PathVariable("id") Long id){
        Long apply = service.delete(id);
        return new CommonResult().success(apply);
    }

    @GetMapping("/{id}")
    public Object get(@PathVariable("id") Long id){
        ReturnApply apply = service.get(id);
        return new CommonResult().success(apply);
    }
}
