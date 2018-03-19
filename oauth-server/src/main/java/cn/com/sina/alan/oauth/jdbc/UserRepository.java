package cn.com.sina.alan.oauth.jdbc;

import java.util.List;

import cn.com.sina.alan.oauth.model.User;

/**
 * @author Shengzhao Li
 */

public interface UserRepository {

    User findByGuid(String guid);

    void saveUser(User user);

    void updateUser(User user);

    User findByUsername(String username);

    List<User> findUsersByUsername(String username);
}