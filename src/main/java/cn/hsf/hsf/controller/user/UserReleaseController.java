package cn.hsf.hsf.controller.user;

import cn.hsf.hsf.pojo.user.UserRelease;
import cn.hsf.hsf.service.user.UserReleaseService;
import cn.hsf.hsf.utils.DateUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    /**
     * 保存新增 发布
     * "title":title,"nickName":nickName,"phone":phone,"serviceProvince":serviceProvince,"serviceCity":serviceCity,"serviceArea":serviceArea,"serverDetail":serverDetail,"appointTime":appointTime,"demand":demand
     * @return
     */
    @RequestMapping("/insUserRelease")
    @ResponseBody
    public boolean insUserRelease(String title,String  nickName,String phone,@RequestParam(value = "serviceProvince",required = false,defaultValue = "-1") Integer serviceProvince,
            @RequestParam(value = "serviceCity",required = false,defaultValue = "-1") Integer serviceCity,@RequestParam(value = "serviceArea",required = false,defaultValue = "-1") Integer serviceArea,
                                 String serverDetail,@RequestParam("appointTime") String appointTime ,String demand){
        System.out.println("appointTime："+ appointTime);
        UserRelease userRelease = new UserRelease(title,nickName,phone,serviceProvince,serviceCity,serviceArea,serverDetail, DateUtil.StrToDate2(appointTime),demand);
        System.out.println(userRelease);
        return releaseService.insertUserRelease(userRelease) > 0 ? true : false;
    }

    @RequestMapping("/goMapView")
    public String goMapView(){
        return "MapView";
    }
}
