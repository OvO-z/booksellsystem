package com.bss.order.restapi.controller;

import com.bss.order.domain.dto.CommonResult;
import com.bss.order.object.dto.ReasonParam;
import com.bss.order.object.model.ReturnReason;
import com.bss.order.restapi.service.ReturnReasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by QAQ on 2019/5/31
 */

@RestController
@RequestMapping("/returnReason")
public class ReturnReasonController {

    @Autowired
    ReturnReasonService reasonService;

    @GetMapping("/listAll")
    public Object getListAll(){
        List<ReturnReason> reasons = reasonService.getListAll();
        return new CommonResult().success(reasons);
    }

    @GetMapping("/list")
    public Object getList( @RequestParam("pageNum") Integer pageNum,
                           @RequestParam("pageSize") Integer pageSize){
        List<ReturnReason> reasons = reasonService.getList(pageNum,pageSize);
        return new CommonResult().pageSuccess(reasons);
    }

    @GetMapping("/{id}")
    public Object get(@PathVariable("id") Long id){
        ReturnReason reason = reasonService.get(id);
        return new CommonResult().success(reason);
    }

    @PostMapping
    public Object create(@RequestBody ReasonParam param){
        ReturnReason reason = reasonService.create(param);
        return new CommonResult().success(reason);
    }

    @PutMapping("/{id}")
    public Object update(@PathVariable("id") Long id, @RequestBody ReasonParam param){
         ReturnReason reason = reasonService.update(id,param);
         return new CommonResult().success(reason);
    }

    @DeleteMapping("/{id}")
    public Object delete(@PathVariable("id") Long id){
        long reason = reasonService.delete(id);
        return new CommonResult().success(reason);
    }
}
