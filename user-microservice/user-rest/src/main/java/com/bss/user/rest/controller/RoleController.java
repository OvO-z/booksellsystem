package com.bss.user.rest.controller;

import com.bss.user.domain.dto.CommonResult;
import com.bss.user.domain.model.Role;
import com.bss.user.rest.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by QAQ on 2019/5/9
 */
@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    RoleService roleService;

    @GetMapping("/listEnable")
    public Object listEnable(){
        List<Role> roles = roleService.listEnable();
        return new CommonResult().success(roles);
    }

    @GetMapping
    public Object getByAdminId(@RequestParam("adminId") Integer adminId){
        List<Role> roles = roleService.getByAdminId(adminId);
        return new CommonResult().success(roles);
    }

    @GetMapping("/list")
    public Object list(){
        List<Role> roles = roleService.list();
        return new CommonResult().success(roles);
    }
}
