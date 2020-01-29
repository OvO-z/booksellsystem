package com.bss.user.auth.service.impl;

import com.bss.user.domain.dto.AdminParam;
import com.bss.user.auth.service.AdminService;
import com.bss.user.auth.util.JwtTokenUtil;
import com.bss.user.domain.dao.AdminRoleDao;
import com.bss.user.domain.mapper.AdminMapper;
import com.bss.user.domain.mapper.AdminRoleMapper;
import com.bss.user.domain.model.Admin;
import com.bss.user.domain.model.AdminExample;
import com.bss.user.domain.model.AdminRole;
import com.bss.user.domain.model.Role;
import com.github.pagehelper.PageHelper;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by QAQ on 2019/5/8
 */
@Log4j
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminMapper adminMapper;

    @Autowired
    AdminRoleMapper adminRoleMapper;

    @Autowired
    AdminRoleDao adminRoleDao;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Admin getAdminByUsername(String username) {
        AdminExample example = new AdminExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<Admin> admins = adminMapper.selectByExample(example);
        if (admins != null && admins.size() > 0){
            return admins.get(0);
        }
        return null;
    }

    @Override
    public List<Role> getRolesByAdminId(Integer adminId) {
        return adminRoleDao.getRoles(adminId);
    }

    @Override
    public String login(String username, String password) {
        String token = null;
        //密码需要客户端加密后传递
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if(!passwordEncoder.matches(password,userDetails.getPassword())){
                throw new BadCredentialsException("密码不正确");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);
        } catch (AuthenticationException e) {
            log.error("登录异常:{"+ e.getMessage()+"}");
        }
        return token;
    }

    @Override
    public Admin register(AdminParam param) {
        Admin umsAdmin = new Admin();
        BeanUtils.copyProperties(param, umsAdmin);
        umsAdmin.setCreateTime(new Date());
        umsAdmin.setStatus(1);
        //查询是否有相同用户名的用户
        AdminExample example = new AdminExample();
        example.createCriteria().andUsernameEqualTo(umsAdmin.getUsername());
        List<Admin> umsAdminList = adminMapper.selectByExample(example);
        if (umsAdminList.size() > 0) {
            return null;
        }
        //将密码进行加密操作
        String encodePassword = passwordEncoder.encode(umsAdmin.getPassword());
        umsAdmin.setPassword(encodePassword);
        adminMapper.insert(umsAdmin);
        for (Integer roleId :  param.getRoles()){
            AdminRole role = new AdminRole();
            role.setAdminId(umsAdmin.getId());
            role.setRoleId(roleId);
            adminRoleMapper.insertSelective(role);
        }
        return umsAdmin;
    }

    @Override
    public String refreshToken(String oldToken) {
        String token = oldToken.substring(tokenHead.length());
        if (jwtTokenUtil.canRefresh(token)) {
            return jwtTokenUtil.refreshToken(token);
        }
        return null;
    }

    @Override
    public Admin getItem(Integer id) {
        Admin admin = adminMapper.selectByPrimaryKey(id);
        return admin;
    }

    @Override
    public List<Admin> list(String name, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        AdminExample example = new AdminExample();
        AdminExample.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(name)) {
            criteria.andUsernameLike("%" + name + "%");
            example.or(example.createCriteria().andNickNameLike("%" + name + "%"));
        }
        return adminMapper.selectByExample(example);
    }
}
