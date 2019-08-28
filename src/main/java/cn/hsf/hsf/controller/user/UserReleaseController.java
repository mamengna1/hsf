package cn.hsf.hsf.controller.user;

import cn.hsf.hsf.pojo.user.UserRelease;
import cn.hsf.hsf.service.message.MessageService;
import cn.hsf.hsf.service.user.UserReleaseService;
import cn.hsf.hsf.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/zsf")
public class UserReleaseController {

    @Resource
    private UserReleaseService releaseService;

    @Autowired
    private MessageService messageService;

    /**
     * 进入找师傅的页面
     *
     * @return
     */
    @RequestMapping("/goZsfView")
    public String goZsfView() {
        return "zsf";
    }

    /**
     * 保存新增 发布
     * "title":title,"nickName":nickName,"phone":phone,"serviceProvince":serviceProvince,"serviceCity":serviceCity,"serviceArea":serviceArea,"serverDetail":serverDetail,"appointTime":appointTime,"demand":demand
     *
     * @return
     */
    @RequestMapping("/insUserRelease")
    @ResponseBody
    public boolean insUserRelease(String title, String nickName, String phone, @RequestParam(value = "serviceProvince", required = false, defaultValue = "-1") Integer serviceProvince,
                                  @RequestParam(value = "serviceCity", required = false, defaultValue = "-1") Integer serviceCity, @RequestParam(value = "serviceArea", required = false, defaultValue = "-1") Integer serviceArea,
                                  String serverDetail, @RequestParam("appointTime") String appointTime, String demand, HttpServletRequest request) {
        System.out.println("appointTime：" + appointTime);
        UserRelease userRelease = new UserRelease(title, nickName, phone, serviceProvince, serviceCity, serviceArea, serverDetail, DateUtil.StrToDate2(appointTime), demand, (Integer) request.getSession().getAttribute("uid"));
        System.out.println(userRelease);

        int count = releaseService.insertUserRelease(userRelease);


        if (count > 0) {
            Map map = new HashMap();
            map.put("openId", request.getSession().getAttribute("openId"));
            map.put("template_id", "TF2-OgTgYB6EYKzmno0NjbZobdCadK7U0d0E9O9ZogA");
            map.put("url", "http://java.86blue.cn/_api/goUserOrderDetail?id=" + userRelease.getId());
            map.put("title", "需求发布成功，请等待师傅接单");
            map.put("serviceType", userRelease.getTitle());
            map.put("orderNo", System.currentTimeMillis() + "");
            map.put("orderState", "待接单");
            map.put("end", "师傅速达正在为您提供派单服务。");
            messageService.sendZhaoSf(map);
        }

        return count > 0 ? true : false;
    }

    @RequestMapping("/goMapView")
    public String goMapView() {
        return "MapView";
    }
}
