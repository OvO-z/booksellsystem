package com.bss.user.auth.controller;

import com.bss.user.domain.dto.CommonResult;
import com.bss.user.domain.dto.AdminLoginParam;
import com.bss.user.domain.dto.AdminParam;
import com.bss.user.auth.service.AdminService;
import com.bss.user.domain.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by QAQ on 2019/5/8
 */
@RestController
public class AdminController {

    @Autowired
    private AdminService adminService;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Object login(@RequestBody AdminLoginParam param, BindingResult result) {
        String token = adminService.login(param.getUsername(), param.getPassword());
        if (token == null) {
            return new CommonResult().validateFailed("用户名或密码错误");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return new CommonResult().success(tokenMap);
    }

    @GetMapping("/isExitUser")
    public Object isExit(@RequestParam("username") String username){
        Admin admin = adminService.getAdminByUsername(username);
        Boolean isExit = false;
        if (admin == null) {
            isExit = true;
        }
        return new CommonResult().success(isExit);
    }

    @GetMapping("/infos")
    @ResponseBody
    public Object getAdminInfo(Principal principal) {
        String username = principal.getName();
        Admin admin = adminService.getAdminByUsername(username);
        Map<String, Object> data = new HashMap<>();
        data.put("id",admin.getId());
        data.put("username", admin.getUsername());
        data.put("roles", adminService.getRolesByAdminId(admin.getId()));
        data.put("icon", admin.getIcon());
        return new CommonResult().success(data);
    }

    @PostMapping("/register")
    public Object register(@RequestBody AdminParam param){
        Admin umsAdmin = adminService.register(param);
        if (umsAdmin == null) {
            new CommonResult().failed();
        }
        return new CommonResult().success(umsAdmin);
    }

    @GetMapping("/token/refresh")
    @ResponseBody
    public Object refreshToken(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String refreshToken = adminService.refreshToken(token);
        if (refreshToken == null) {
            return new CommonResult().failed();
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", refreshToken);
        tokenMap.put("tokenHead", tokenHead);
        return new CommonResult().success(tokenMap);
    }

    @GetMapping("/list")
    @ResponseBody
    public Object list(@RequestParam(value = "name",required = false) String name,
                       @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                       @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum){
        List<Admin> adminList = adminService.list(name,pageSize,pageNum);
        return new CommonResult().pageSuccess(adminList);
    }

    @GetMapping("/{id}")
    public Object get(@PathVariable("id") Integer id){
        Admin admin = adminService.getItem(id);
        return new CommonResult().success(admin);
    }

    @GetMapping("/logout")
    @ResponseBody
    public Object logout() {
        return new CommonResult().success(null);
    }
}
