package cn.hsf.hsf.controller.user;

import cn.hsf.hsf.pojo.user.UserDetail;
import cn.hsf.hsf.pojo.user.UserInformation;
import cn.hsf.hsf.service.user.UserDetailService;
import cn.hsf.hsf.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

/**
 * @author kaituozhe
 */
@Controller
public class SFController {

    @Autowired
    private UserDetailService userDetailService;
    @Autowired
    private UserService userService;

    @RequestMapping("/goSFList")
    public String goSFList(String skill, Model model) {
        model.addAttribute("skill", skill);
        return "sf/sflist";
    }

    @ResponseBody
    @RequestMapping("/selSFList")
    public List<UserDetail> selSFList(UserDetail userDetail) {
        System.out.println(userDetail);
        // 根据技能查询所有师傅
        List<UserDetail> userDetails = userDetailService.selBySkill(userDetail);
        for (UserDetail u : userDetails) {
            // 师傅的技能列表
            u.setSkillList(userDetailService.selSkillById(Arrays.asList(u.getSkills().split(","))));
        }
        return userDetails;
    }

    @RequestMapping("/delDynamic")
    public String delDynamic(Integer id) {
        userDetailService.delDynamic(id);
        return "redirect:/goSFHone";
    }

    /**
     * 根据师傅openid查询师傅的动态
     */
    @ResponseBody
    @RequestMapping("/searchSfInfo")
    public List<UserInformation> searchSfInfo(Integer id) {

        String openId = userService.selByDetailId(id).getOpenId();
        // 师傅的动态
        List<UserInformation> infos = userDetailService.selInfoByOpenId(openId);
        System.out.println("INFOS : " + infos);
        return infos;
    }

}
