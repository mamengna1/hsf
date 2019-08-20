package cn.hsf.hsf.controller.user;

import cn.hsf.hsf.pojo.user.UserRelease;
import cn.hsf.hsf.service.user.UserReleaseService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/zsf")
public class UserReleaseController {

    @Resource
    private UserReleaseService releaseService;

    /**
     * 进入找师傅的页面
     * @return
     */
    @RequestMapping("/goZsfView")
    public String goZsfView(){
        return "zsf";
    }

    @RequestMapping("/insUserRelease")
    @ResponseBody
    public boolean insUserRelease(@RequestBody UserRelease userRelease){
        System.out.println(userRelease);
        return releaseService.insertUserRelease(userRelease) > 0 ? true : false;
    }
}
