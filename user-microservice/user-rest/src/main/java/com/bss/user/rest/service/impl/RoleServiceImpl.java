package com.bss.user.rest.service.impl;

import com.bss.user.domain.dao.AdminRoleDao;
import com.bss.user.domain.mapper.RoleMapper;
import com.bss.user.domain.model.Role;
import com.bss.user.domain.model.RoleExample;
import com.bss.user.rest.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by QAQ on 2019/5/9
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    AdminRoleDao adminRoleDao;

    @Override
    public List<Role> listEnable() {
        Role role = new Role();
        role.setStatus(1);
        RoleExample example = new RoleExample();
        example.createCriteria().andStatusEqualTo(role.getStatus());
        return roleMapper.selectByExample(example);
    }

    @Override
    public List<Role> getByAdminId(Integer adminId) {
        return adminRoleDao.getRoles(adminId);
    }

    @Override
    public List<Role> list() {
        RoleExample example = new RoleExample();
        return roleMapper.selectByExample(example);
    }
}
