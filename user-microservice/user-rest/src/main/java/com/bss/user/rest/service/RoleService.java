package com.bss.user.rest.service;

import com.bss.user.domain.model.Role;

import java.util.List;

/**
 * Created by QAQ on 2019/5/9
 */
public interface RoleService {

    List<Role> listEnable();

    List<Role> getByAdminId(Integer adminId);

    List<Role> list();
}
