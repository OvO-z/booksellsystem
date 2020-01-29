package com.bss.order.restapi.controller;

import com.bss.order.domain.dto.CommonResult;
import com.bss.order.domain.dto.OrderParam;
import com.bss.order.domain.dto.OrderWithItem;
import com.bss.order.object.dto.OrderQueryParam;
import com.bss.order.object.model.OrderItem;
import com.bss.order.object.model.Orders;
import com.bss.order.restapi.service.OrderService;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by QAQ on 2019/5/26
 */
@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping
    public Object createOrder(@RequestBody OrderParam param){
        Long id = orderService.create(param);
//        orderChannel.send(MessageBuilder.withBody().build());
        return new CommonResult().success(id);
    }

    @GetMapping("/{id}")
    public Object get(@PathVariable("id") Long id){
        Orders orders = orderService.get(id);
        return new CommonResult().success(orders);
    }

    @PutMapping("/{id}")
    public Object update(@PathVariable("id") Long id, @RequestBody OrderParam param){
        Orders order = orderService.update(id, param);
        return new CommonResult().success(order);
    }

    @PutMapping("/{id}/status")
    public Object updateStatus(@PathVariable("id") Long id, @RequestParam("status") Integer status){
        Orders order = orderService.updateStatus(id,status);
        return new CommonResult().success(order);
    }

    @GetMapping("/list")
    public Object listAll(OrderQueryParam param, @RequestParam("pageNum") Integer pageNum,
                          @RequestParam("pageSize") Integer pageSize){
        List<Orders> orders = orderService.listAll(param,pageNum, pageSize);
        return new CommonResult().pageSuccess(orders);
    }

    @GetMapping("/items")
    public Object getItems(Long id){
        List<OrderItem> items = orderService.getItems(id);
        return new CommonResult().pageSuccess(items);
    }

    @GetMapping("/listWithItems")
    public Object getListWithItems(@RequestParam("userId") Long id,Integer pageNum, Integer pageSize){
        List<OrderWithItem> list = orderService.listWithItems(id,pageNum,pageSize);
        return new CommonResult().pageSuccess(list);
    }
}
