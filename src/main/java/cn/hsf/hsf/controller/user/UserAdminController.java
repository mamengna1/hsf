package cn.hsf.hsf.controller.user;

import cn.hsf.hsf.pojo.user.UserDetail;
import cn.hsf.hsf.service.user.UserDetailService;
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

    @ResponseBody
    @RequestMapping("/updLineStatus")
    public boolean updLineStatus(UserDetail userDetail, HttpSession session) {
        userDetail.setId((Integer) session.getAttribute("detailId"));
        return userDetailService.updUserLineStatus(userDetail) > 0;
    }

}
