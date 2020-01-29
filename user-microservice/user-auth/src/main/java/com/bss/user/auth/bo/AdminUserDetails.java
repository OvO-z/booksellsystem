package com.bss.user.auth.bo;

import com.bss.user.domain.model.Admin;
import com.bss.user.domain.model.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by QAQ on 2019/5/8
 */
public class AdminUserDetails implements UserDetails {

    private Admin admin;
    private List<Role> roles;

    public AdminUserDetails(Admin admin, List<Role> roles) {
        this.admin = admin;
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .filter(role -> role.getKeywords() != null)
                .map(role -> new SimpleGrantedAuthority(role.getKeywords()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return admin.getPassword();
    }

    @Override
    public String getUsername() {
        return admin.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return admin.getStatus().equals(1);
    }
}
