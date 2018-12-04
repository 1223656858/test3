package cn.itcast.exam.service.impl;

import cn.itcast.exam.dao.UserDao;
import cn.itcast.exam.dao.impl.UserDaoImpl;
import cn.itcast.exam.domain.ResultInfo;
import cn.itcast.exam.domain.User;
import cn.itcast.exam.service.UserService;

public class UserServiceImpl implements UserService {
    private UserDao dao = new UserDaoImpl();

    @Override
    public boolean registr(User user) {
        //根据用户名判断是否能注册
        User u = dao.findByUsername(user.getUsername());
        ResultInfo info = new ResultInfo();
        if (u != null) {
            //用户存在
            info.setFlag(false);
            info.setErrorMsg("用户已存在，请重新注册");
            return false;
        } else {
            //用户不存在
            info.setFlag(true);
            info.setErrorMsg("可以注册");
        }
        return true;
    }

    @Override
    public User login(User user) {
        return dao.findByUsernameAndPassword(user.getUsername(), user.getPassword());
    }
}
