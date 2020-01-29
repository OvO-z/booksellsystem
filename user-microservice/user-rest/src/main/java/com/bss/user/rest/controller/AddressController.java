package com.bss.user.rest.controller;

import com.bss.user.domain.dto.AddressParam;
import com.bss.user.domain.dto.CommonResult;
import com.bss.user.domain.model.Address;
import com.bss.user.rest.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by QAQ on 2019/5/24
 */

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    AddressService addressService;

    @GetMapping
    public Object get(@RequestParam("userId") Long userId){
        List<Address> addresses = addressService.get(userId);
        return new CommonResult().success(addresses);
    }

    @DeleteMapping("/{id}")
    public Object delete(@PathVariable("id") Integer id){
        int address = addressService.delete(id);
        if (address >= 1)
        return new CommonResult().success(address);
        return new CommonResult().failed();
    }

    @PutMapping("/{id}")
    public Object update(@PathVariable("id") Integer id,@RequestBody AddressParam param){
        Address address = addressService.update(id,param);
        return new CommonResult().success(address);
    }

    @PostMapping
    public Object create(@RequestBody AddressParam param){
        Address address = addressService.create(param);
        return new CommonResult().success(address);
    }
}
