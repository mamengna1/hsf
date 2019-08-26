package cn.hsf.hsf.controller.user;

import cn.hsf.hsf.pojo.user.User;
import cn.hsf.hsf.pojo.user.UserDetail;
import cn.hsf.hsf.service.user.UserDetailService;
import cn.hsf.hsf.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class RegisterController {

    private static Double[] score = {5.0, 2.0};

    @Autowired
    private UserService userService;
    @Autowired
    private UserDetailService userDetailService;

    @RequestMapping("/insUserDetail")
    public String insUserDetail(UserDetail userDetail, String phone, HttpSession session) {
        System.out.println("师傅信息" + userDetail);
//        userDetail.setAddress(provinceName + cityName + areaName + userDetail.getAddress());
        if (userDetail.getStatus() == 2) {
            System.out.println("重新提交");
            userDetail.setStatus(3);
            userDetailService.updUserLineStatus(userDetail);
        } else if (userDetail.getStatus() == 1) {
            userDetailService.updUserLineStatus(userDetail);
        } else {
            userDetailService.insUserDetail(userDetail);
        }
        String openId = (String) session.getAttribute("openId");
        userService.updUser(new User(openId, phone, userDetail.getId()));
        return "redirect:/goIndex";
    }

}
