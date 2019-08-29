package cn.hsf.hsf.controller.user;

import cn.hsf.hsf.pojo.user.UserDetail;
import cn.hsf.hsf.service.user.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;

/**
 * @author kaituozhe
 */
@Controller
public class SFController {

    @Autowired
    private UserDetailService userDetailService;

    @RequestMapping("/goSFList")
    public String goSFList(String skill, Model model){
        model.addAttribute("skill", skill);
        return "sf/sflist";
    }

    @ResponseBody
    @RequestMapping("/selSFList")
    public List<UserDetail> selSFList(UserDetail userDetail){
        System.out.println(userDetail);
        // 根据技能查询所有师傅
        List<UserDetail> userDetails = userDetailService.selBySkill(userDetail);
        for (UserDetail u : userDetails){
            // 师傅的技能列表
            u.setSkillList(userDetailService.selSkillById(Arrays.asList(u.getSkills().split(","))));
        }
        return userDetails;
    }

    @RequestMapping("/delDynamic")
    public String delDynamic(Integer id){
        userDetailService.delDynamic(id);
        return "redirect:/goSFHone";
    }

}
