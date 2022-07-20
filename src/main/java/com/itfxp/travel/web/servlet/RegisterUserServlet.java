package com.itfxp.travel.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itfxp.travel.domain.ResultInfo;
import com.itfxp.travel.domain.User;
import com.itfxp.travel.service.UserService;
import com.itfxp.travel.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/registerUserServlet")
public class RegisterUserServlet  extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // get请求中编写

        // 获取请求参数
        Map<String, String[]> map = req.getParameterMap();

        // 封装数据
        User user = new User();
        try {
            BeanUtils.populate(user,map);
            System.out.println("user:" + user);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        // 调用service中的方法处理逻辑
        UserService userService = new UserServiceImpl();
        boolean flag = userService.register(user);

        // 根据service中的方法的返回结果，进行封装数据
        // true 代表注册成功  false 代表注册失败
        ResultInfo resultInfo = new ResultInfo();
        if (flag) {
            // 封装数据
            resultInfo.setFlag(true);
        }else {
            // 封装数据，设置提示信息。
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("注册失败！");
        }

        // 将结果转化json数据，进行响应，同时要设置响应头
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(resultInfo);

        // 设置响应头
        resp.setContentType("application/json;charset=utf-8");
        // 写数据
        resp.getWriter().write(json);



    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       this.doGet(req, resp);
    }
}
