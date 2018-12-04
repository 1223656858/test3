package cn.itcast.exam.service;

import cn.itcast.exam.domain.User;

public interface UserService {
    boolean registr(User user);

    User login(User user);

}
