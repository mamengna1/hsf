package cn.hsf.hsf.controller.user;

import cn.hsf.hsf.pojo.user.UserDetail;
import cn.hsf.hsf.pojo.user.UserInformation;
import cn.hsf.hsf.service.user.UserDetailService;
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
public class UserAdminController {

    @Autowired
    private UserDetailService userDetailService;
    @Autowired
    private UserService userService;

    /**
     *  修改用户在线状态
     * @param userDetail
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping("/updLineStatus")
    public boolean updLineStatus(UserDetail userDetail, HttpSession session) {
        userDetail.setId((Integer) session.getAttribute("detailId"));
        System.out.println(userDetail);
        return userDetailService.updUserLineStatus(userDetail) > 0;
    }

//    @ResponseBody
    @RequestMapping("/insInformation")
    public String insInformation(UserInformation userInformation, HttpSession session){
        userInformation.setOpenId((String) session.getAttribute("openId"));
        userService.insInformation(userInformation);
        // return userService.insInformation(userInformation) > 0;
        return "redirect:/goSFHone";
    }


}
