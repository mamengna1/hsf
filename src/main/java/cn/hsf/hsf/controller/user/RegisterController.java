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

    @Autowired
    private UserService userService;
    @Autowired
    private UserDetailService userDetailService;

    @RequestMapping("/insUserDetail")
    public String insUserDetail(UserDetail userDetail, String phone, HttpSession session) {
        System.out.println("师傅信息" + userDetail);
        userDetailService.insUserDetail(userDetail);
        userService.updUser(new User((String) session.getAttribute("openId"), phone, userDetail.getId()));
        return "/_api/index";
    }

}
