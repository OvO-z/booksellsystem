package com.bss.user.auth.service;

import com.bss.user.domain.dto.AdminParam;
import com.bss.user.domain.model.Admin;
import com.bss.user.domain.model.Role;

import java.util.List;

/**
 * Created by QAQ on 2019/5/8
 */
public interface AdminService {
    /**
     * 根据用户名获取后台管理员
     */
    Admin getAdminByUsername(String username);

    List<Role> getRolesByAdminId(Integer adminId);

    String login(String username, String password);

    /**
     * 注册功能
     */
    Admin register(AdminParam param);

    /**
     * 刷新token的功能
     * @param oldToken 旧的token
     */
    String refreshToken(String oldToken);

    /**
     * 根据用户id获取用户
     */
    Admin getItem(Integer id);

    /**
     * 根据用户名或昵称分页查询用户
     */
    List<Admin> list(String name, Integer pageSize, Integer pageNum);

}
