package cn.hsf.hsf.controller.user;

import cn.hsf.hsf.pojo.user.Distribution;
import cn.hsf.hsf.pojo.user.User;
import cn.hsf.hsf.pojo.user.UserRelease;
import cn.hsf.hsf.service.message.MessageService;
import cn.hsf.hsf.service.user.DistributionService;
import cn.hsf.hsf.service.user.UserReleaseService;
import cn.hsf.hsf.service.user.UserService;
import cn.hsf.hsf.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    @Autowired
    private UserService userService;
    @Autowired
    private DistributionService distributionService;

    /**
     * 进入找师傅的页面
     * id : 师傅ID
     *
     * @return
     */
    @RequestMapping("/goZsfView")
    public String goZsfView(Integer id, Model model) {
        model.addAttribute("id", id);
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
    public boolean insUserRelease(Integer id, String title, String nickName, String phone, @RequestParam(value = "serviceProvince", required = false, defaultValue = "-1") Integer serviceProvince,
                                  @RequestParam(value = "serviceCity", required = false, defaultValue = "-1") Integer serviceCity, @RequestParam(value = "serviceArea", required = false, defaultValue = "-1") Integer serviceArea,
                                  String serverDetail, @RequestParam("appointTime") String appointTime, String demand, HttpServletRequest request) {
        // id = 师傅ID
        UserRelease userRelease = new UserRelease(title, nickName, phone, serviceProvince, serviceCity, serviceArea, serverDetail, DateUtil.StrToDate2(appointTime), demand, (Integer) request.getSession().getAttribute("uid"));
        System.out.println(userRelease);

        int count = releaseService.insertUserRelease(userRelease);

        if (count > 0) {
            Map map = new HashMap();
            User user = null; // 师傅
            System.out.println("USER " + user);
            // 发送给师傅   直接预约的师傅
            if (id != null) {
                user = userService.selByDetailId(id);
                Distribution distribution = new Distribution(userRelease.getId(), 1, id);
                distributionService.insOrder(distribution);
                map.put("openId", user.getOpenId());
                map.put("template_id", "TF2-OgTgYB6EYKzmno0NjbZobdCadK7U0d0E9O9ZogA");
                map.put("url", "http://java.86blue.cn/_api/goOrderShow?id=" + distribution.getId());
                map.put("title", "您有一个新的雇佣订单等待处理。");
                map.put("serviceType", userRelease.getTitle());
                map.put("orderNo", userRelease.getId() + "");
                map.put("orderState", "待接单");
                map.put("end", "点击查看详情，进入接单中心");
                messageService.sendZhaoSf(map);
            }

            // 预约发布成功  业主收到通知
            map.put("openId", request.getSession().getAttribute("openId"));
            map.put("template_id", "TF2-OgTgYB6EYKzmno0NjbZobdCadK7U0d0E9O9ZogA");
            map.put("url", "http://java.86blue.cn/_api/goUserOrderDetail?id=" + userRelease.getId());
            map.put("title", "需求发布成功，请等待师傅接单");
            map.put("serviceType", userRelease.getTitle());
            map.put("orderNo", userRelease.getId() + "");
            map.put("orderState", "待接单");
            map.put("end", "师傅速达正在为您提供派单服务。");
            messageService.sendZhaoSf(map);

            // 预约发布成功  平台管理员收到的通知
            map.put("openId", "o5uJY1sfH745I9vnaw06RuhMDRdc");
            map.put("template_id", "TF2-OgTgYB6EYKzmno0NjbZobdCadK7U0d0E9O9ZogA");
            map.put("url", "http://java.86blue.cn/_api/goUserOrderDetail?id=" + userRelease.getId());
            map.put("title", "师傅速达有新订单啦！");
            map.put("serviceType", userRelease.getTitle());
            map.put("orderNo", userRelease.getId() + "");
            map.put("orderState", "待接单");
            map.put("end", "业主信息：" + nickName + " : " + phone + "雇佣师傅 ：" + (id == null ? "未选择师傅" : user.getUserDetail().getName() + " : " + user.getPhone()));
            messageService.sendZhaoSf(map);

        }
        return count > 0 ? true : false;
    }

    /**
     *  地图
     * @return
     */
    @RequestMapping("/goMapView")
    public String goMapView() {
        return "MapView";
    }
}
