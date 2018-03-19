package cn.com.sina.alan.oauth.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import cn.com.sina.alan.oauth.jdbc.UserRepository;
import cn.com.sina.alan.oauth.model.User;
import cn.com.sina.alan.oauth.model.WdcyUserDetails;
import cn.com.sina.alan.oauth.service.UserService;

/**
 * 处理用户, 账号, 安全相关业务
 *
 * @author Shengzhao Li
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    public UserServiceImpl() {
    	System.out.println("init");
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null || user.archived()) {
            throw new UsernameNotFoundException("Not found any user for username[" + username + "]");
        }

        return new WdcyUserDetails(user);
    }


    @Override
    public boolean isExistedUsername(String username) {
        final User user = userRepository.findByUsername(username);
        return user != null;
    }


	@Override
	public User findUserByMobile(String mobile) {
		return null;
	}
}