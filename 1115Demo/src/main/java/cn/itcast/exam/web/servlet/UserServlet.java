package cn.itcast.exam.web.servlet;

import cn.itcast.exam.dao.UserDao;
import cn.itcast.exam.dao.impl.UserDaoImpl;
import cn.itcast.exam.domain.ResultInfo;
import cn.itcast.exam.domain.User;
import cn.itcast.exam.service.UserService;
import cn.itcast.exam.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet {
    //注册
    public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String check = request.getParameter("check");

        HttpSession session = request.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");

        session.removeAttribute("CHECKCODE_SERVER");

        if (checkcode_server.equalsIgnoreCase(check)){
            ResultInfo info = new ResultInfo();

            info.setFlag(false);
            info.setErrorMsg("验证码错误");
            return;
        }

        //获取参数
        Map<String, String[]> map = request.getParameterMap();
        //封装User对象
        User user = new User();

        try {
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


        UserService service = new UserServiceImpl();
        boolean flag = service.registr(user);

        ResultInfo info = new ResultInfo();
        //判断用户是否为空
        if (flag) {
            //用户存在
            info.setFlag(false);
            info.getErrorMsg("用户已存在，请重新注册");
        } else {
            //用户不存在
            info.setFlag(true);
            info.getErrorMsg("注册成功");
        }
        writeValue(info, response);
    }

    //登录
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> map = request.getParameterMap();

        User user = new User();

        try {
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        UserService service = new UserServiceImpl();
        User u = service.login(user);

        ResultInfo info = new ResultInfo();

        if (u==null){
            //用户不存在
            info.setFlag(false);
            info.setErrorMsg("用户不存在，请注册");
        }else {
            //用户存在
            info.setFlag(true);
            info.setErrorMsg("登录成功");
        }
        writeValue(u,response);
    }
/*
    public void(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    public void(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    public void(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    public void(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }*/
}
