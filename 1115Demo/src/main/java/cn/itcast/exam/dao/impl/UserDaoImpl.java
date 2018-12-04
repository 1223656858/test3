package cn.itcast.exam.dao.impl;

import cn.itcast.exam.dao.UserDao;
import cn.itcast.exam.domain.User;
import cn.itcast.exam.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDaoImpl implements UserDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public User findByUsername(String username) {
        String sql = "select * from tab_user where username = ? ";
        User user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username);
        return user;
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        String sql="select * from tab_user where username = ? and password = ? ";
        User user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username, password);
        return user;
    }
}
