package com.maritree.zuul.security.service;


import com.maritree.zuul.AuthorityFeignService;
import com.maritree.zuul.common.response.Result;
import com.maritree.zuul.security.entity.JwtUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by echisan on 2018/6/23
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AuthorityFeignService authorityFeignService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Result<Map<String, Object>> result = authorityFeignService.getUserDetails(userName);
        Map<String, Object> userMap = result.getData();
        if (userMap == null || userMap.isEmpty()) {
            throw new UsernameNotFoundException("未找到用户");
        }
        return new JwtUser(userMap);
    }

}
