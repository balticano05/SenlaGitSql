package com.online.shop.security.jwt;

import com.online.shop.entity.Role;
import com.online.shop.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

public final class JwtUserFactory {

    public static JwtUser createUser(User user) {
        return new JwtUser(user.getId(), user.getPassword(), user.getEmail(), true, user.getCreatedAt(), mapAuthorities(List.of(user.getRole())));
    }

    private static List<GrantedAuthority> mapAuthorities(List<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }

}
