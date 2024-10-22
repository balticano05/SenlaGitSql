package com.online.shop.security;

import com.online.shop.entity.User;
import com.online.shop.security.jwt.JwtUser;
import com.online.shop.security.jwt.JwtUserFactory;
import com.online.shop.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.info("Executing method in loadUserByUsername");
        User user = modelMapper.map(userService.findByEmail(email), User.class);
        if (user == null) {
            throw new UsernameNotFoundException("User with username: " + email + " not found");
        }
        JwtUser jwtUser = JwtUserFactory.createUser(user);
        log.info("User with email: {} successfully loaded", email);
        return jwtUser;
    }
}
