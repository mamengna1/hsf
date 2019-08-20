package cn.hsf.hsf.controller.back;

import cn.hsf.hsf.pojo.back.CashBack;
import cn.hsf.hsf.pojo.user.User;
import cn.hsf.hsf.service.back.CashBackService;
import cn.hsf.hsf.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @author kaituozhe
 */
@Controller
public class BackController {

    @Autowired
    private CashBackService cashBackService;
    @Autowired
    private UserService userService;


    @ResponseBody
    @RequestMapping("/insRecord")
    public Integer insRecord(CashBack cashBack, HttpSession session){
        System.out.println("返现对象 ： " +cashBack);
        String openId = (String) session.getAttribute("openId");
        cashBack.setOpenId(openId);
        userService.updUser(new User(openId, cashBack.getMoney()));
        return cashBackService.insRecord(cashBack);
    }

}
