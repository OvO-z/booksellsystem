package com.bss.user.domain.dao;

import com.bss.user.domain.model.Role;

import java.util.List;

/**
 * Created by QAQ on 2019/5/8
 */
public interface AdminRoleDao {
    List<Role> getRoles(Integer adminId);
}
