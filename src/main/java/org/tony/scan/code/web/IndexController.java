package org.tony.scan.code.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tony.scan.code.entity.UserEntity;
import org.tony.scan.code.util.BarcodeUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @version 1.0.0
 * @Description
 * @Date 2017/11/21
 * @Author xuanyi
 * @pageageName is org.tony.scan.code.web
 * @projectName is ScanCodeGunDemo
 */
@Controller
@RequestMapping(value = "/index/")
public class IndexController {

    @GetMapping(value = "home")
    public String index(HttpServletRequest request){
        HttpSession session = request.getSession();
        UserEntity user = (UserEntity) session.getAttribute("user");
        if (user == null){
            return "login";
        }else{
            return "code";
        }
    }

    @PostMapping(value = "login")
    public String login(HttpServletRequest request, @PathParam("name") String name,String password){
        UserEntity userEntity  = new UserEntity();
        userEntity.setId("11233445");
        userEntity.setName(name);
        userEntity.setPassword(password);
        HttpSession session = request.getSession();
        session.setAttribute("user",userEntity);
        return "code";
    }

    @GetMapping(value = "scancode")
    public void login(HttpServletRequest request, HttpServletResponse response){
        UserEntity user = (UserEntity) request.getSession().getAttribute("user");
        BarcodeUtil barcodeUtil = new BarcodeUtil();
        try {
            byte [] code = barcodeUtil.generates(user.getId() + user.getName() + user.getPassword());
            response.setContentType("image/png");
            OutputStream stream = response.getOutputStream();
            stream.write(code);
            stream.flush();
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
