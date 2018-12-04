package cn.itcast.exam.dao;

import cn.itcast.exam.domain.User;

public interface UserDao {
    User findByUsername(String username);

    User findByUsernameAndPassword(String username, String password);
}
