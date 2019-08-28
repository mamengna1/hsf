package cn.hsf.hsf.controller.user;

import cn.hsf.hsf.pojo.user.Distribution;
import cn.hsf.hsf.pojo.user.User;
import cn.hsf.hsf.pojo.user.UserRelease;
import cn.hsf.hsf.service.message.MessageService;
import cn.hsf.hsf.service.user.DistributionService;
import cn.hsf.hsf.service.user.UserReleaseService;
import cn.hsf.hsf.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class DistributionController {

    @Autowired
    private DistributionService distributionService;

    @Autowired
    private MessageService messageService;
    @Autowired
    private UserReleaseService userReleaseService;
    @Autowired
    private UserService userService;

    /**
     * 判断是否有人接单
     *
     * @param releaseId
     * @return
     */
    @ResponseBody
    @RequestMapping("/isReceiving")
    public boolean isReceiving(Integer releaseId) {
        // == 0 表示没有人接单
        return distributionService.selReleaseById(releaseId) == 0;
    }

    @ResponseBody
    @RequestMapping("/receiving")
    public boolean receiving(Distribution distribution, HttpSession session) {
        try {
            return distributionService.receiving(distribution, session);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 拒单
     *
     * @param distribution
     * @return
     */
    @ResponseBody
    @RequestMapping("/turnDown")
    public boolean turnDown(Distribution distribution, HttpSession session) {

        User ud = userService.selUserByOpenId((String) session.getAttribute("openId"));

        System.out.println(distribution);
        Integer releaseId = distribution.getReleaseId();
        UserRelease userRelease = userReleaseService.selReleaseById(releaseId);
        User user = userService.selById(userRelease.getUserId());
        System.out.println(user);


        Map map = new HashMap();
        map.put("openId", session.getAttribute("openId"));
        map.put("template_id", "TF2-OgTgYB6EYKzmno0NjbZobdCadK7U0d0E9O9ZogA");
        map.put("url", "http://java.86blue.cn/_api/goUserOrderDetail?id=" + distribution.getId());
        map.put("title", "您已拒单");
        map.put("serviceType", userRelease.getTitle());
        map.put("orderNo", System.currentTimeMillis() + "");
        map.put("orderState", "已拒单");
        map.put("end", "有疑问请致电0000000");
        messageService.sendZhaoSf(map);

        Map map2 = new HashMap();
        map2.put("openId", user.getOpenId());
        map2.put("template_id", "TF2-OgTgYB6EYKzmno0NjbZobdCadK7U0d0E9O9ZogA");
        map2.put("url", "http://java.86blue.cn/_api/goUserOrderDetail?id=" + distribution.getId());
        map2.put("title", "您的订单已被师傅" + ud.getUserDetail().getName() + "拒绝");
        map2.put("serviceType", userRelease.getTitle());
        map2.put("orderNo", System.currentTimeMillis() + "");
        map2.put("orderState", "已拒单");
        map2.put("end", "有疑问请致电0000000");
        messageService.sendZhaoSf(map2);

        return distributionService.turnDown(distribution) > 0;
    }

    @ResponseBody
    @RequestMapping("/callOf")
    public boolean callOf(Distribution distribution, HttpSession session) {

        User ud = userService.selUserByOpenId((String) session.getAttribute("openId"));

        System.out.println(distribution);
        Integer releaseId = distribution.getReleaseId();
        UserRelease userRelease = userReleaseService.selReleaseById(releaseId);
        User user = userService.selById(userRelease.getUserId());



        Map map = new HashMap();
        map.put("openId", session.getAttribute("openId"));
        map.put("template_id", "TF2-OgTgYB6EYKzmno0NjbZobdCadK7U0d0E9O9ZogA");
        map.put("url", "http://java.86blue.cn/_api/goUserOrderDetail?id=" + distribution.getId());
        map.put("title", "您已取消订单");
        map.put("serviceType", userRelease.getTitle());
        map.put("orderNo", System.currentTimeMillis() + "");
        map.put("orderState", "已取消");
        map.put("end", "有疑问请致电0000000");
        messageService.sendZhaoSf(map);

        Map map2 = new HashMap();
        map2.put("openId", user.getOpenId());
        map2.put("template_id", "TF2-OgTgYB6EYKzmno0NjbZobdCadK7U0d0E9O9ZogA");
        map2.put("url", "http://java.86blue.cn/_api/goUserOrderDetail?id=" + distribution.getId());
        map2.put("title", "您的订单已被师傅" + ud.getUserDetail().getName() + "取消");
        map2.put("serviceType", userRelease.getTitle());
        map2.put("orderNo", System.currentTimeMillis() + "");
        map2.put("orderState", "已取消");
        map2.put("end", "有疑问请致电0000000");
        messageService.sendZhaoSf(map2);

        return distributionService.callOff(distribution) > 0;
    }

    @ResponseBody
    @RequestMapping("/comple")
    public boolean comple(Distribution distribution, HttpSession session) {

        User ud = userService.selUserByOpenId((String) session.getAttribute("openId"));

        System.out.println(distribution);
        Integer releaseId = distribution.getReleaseId();
        UserRelease userRelease = userReleaseService.selReleaseById(releaseId);
        User user = userService.selById(userRelease.getUserId());

        Map map = new HashMap();
        map.put("openId", session.getAttribute("openId"));
        map.put("template_id", "TF2-OgTgYB6EYKzmno0NjbZobdCadK7U0d0E9O9ZogA");
        map.put("url", "http://java.86blue.cn/_api/goUserOrderDetail?id=" + distribution.getId());
        map.put("title", "您已提交完成申请");
        map.put("serviceType", userRelease.getTitle());
        map.put("orderNo", System.currentTimeMillis() + "");
        map.put("orderState", "提交完成申请");
        map.put("end", "有疑问请致电0000000");
        messageService.sendZhaoSf(map);

        Map map2 = new HashMap();
        map2.put("openId", user.getOpenId());
        map2.put("template_id", "TF2-OgTgYB6EYKzmno0NjbZobdCadK7U0d0E9O9ZogA");
        map2.put("url", "http://java.86blue.cn/_api/goUserOrderDetail?id=" + distribution.getId());
        map2.put("title", "师傅" + ud.getUserDetail().getName() + "发起来订单申请完工");
        map2.put("serviceType", userRelease.getTitle());
        map2.put("orderNo", System.currentTimeMillis() + "");
        map2.put("orderState", "提交完成申请");
        map2.put("end", "有疑问请致电0000000");
        messageService.sendZhaoSf(map2);

        return distributionService.comple(distribution) > 0;
    }

}
