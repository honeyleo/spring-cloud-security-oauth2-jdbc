package cn.com.sina.alan.oauth.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import cn.com.sina.alan.oauth.model.User;

/**
 * @author Shengzhao Li
 */
public interface UserService extends UserDetailsService {

    boolean isExistedUsername(String username);

    
    User findUserByMobile(String mobile);
}