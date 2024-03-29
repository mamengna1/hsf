package cn.hsf.hsf.controller.user;

import cn.hsf.hsf.pojo.user.Distribution;
import cn.hsf.hsf.pojo.user.User;
import cn.hsf.hsf.pojo.user.UserOrder;
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
     * @param distribution
     * @return
     */
    @ResponseBody
    @RequestMapping("/turnDown")
    public boolean turnDown(Distribution distribution, HttpSession session) {
        // 师傅信息
        User ud = userService.selUserByOpenId((String) session.getAttribute("openId"));

        System.out.println(distribution);
        Integer releaseId = distribution.getReleaseId();
        UserRelease userRelease = userReleaseService.selReleaseById(releaseId);
        User user = userService.selById(userRelease.getUserId());
        System.out.println(user);

        // 师傅拒单 发送给用户
        Map map2 = new HashMap();
        map2.put("openId", user.getOpenId());
        map2.put("title", ud.getUserDetail().getName() + "师傅拒绝了您的雇佣。请等待平台给您另外安排师傅。");
        map2.put("serviceType", userRelease.getTitle());
        map2.put("orderNo", userRelease.getId() + "");
        map2.put("orderState", "已拒单");
        map2.put("end", "师傅信息：" + ud.getUserDetail().getName() + ":" + ud.getPhone() +
                "\\n请等待师傅上门服务。如有疑问请直接联系师傅手机或平台客服。");
        messageService.sendZhaoSf(map2);

        // 发送给平台
        map2.put("openId", "o5uJY1sfH745I9vnaw06RuhMDRdc");
        map2.put("title", "订单被师傅拒接，请管理员尽快另行派单。");
        map2.put("serviceType", userRelease.getTitle());
        map2.put("orderNo", userRelease.getId() + "");
        map2.put("orderState", "已拒单");
        map2.put("end", "师傅信息：" + ud.getUserDetail().getName() + ":" + ud.getPhone());
        messageService.sendZhaoSf(map2);


        return distributionService.turnDown(distribution) > 0;
    }

    /**
     * 取消订单
     * @param distribution
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping("/callOf")
    public boolean callOf(Distribution distribution, HttpSession session) {

        User ud = userService.selUserByOpenId((String) session.getAttribute("openId"));

        Distribution dis = distributionService.selOrderById(distribution.getId());

        Integer releaseId = distribution.getReleaseId();
        UserRelease userRelease = userReleaseService.selReleaseById(releaseId);
        distribution.setSfId(userRelease.getReceiveId());
        User user = userService.selById(userRelease.getUserId());
        // TODO 处理 url没有的问题          √
        Map map2 = new HashMap();
        map2.put("openId", user.getOpenId());
        map2.put("title", "订单已被师傅取消，请等待平台给您另外安排师傅。");
        map2.put("serviceType", userRelease.getTitle());
        map2.put("orderNo", dis.getOrderId() + "");
        map2.put("orderState", "已取消");
        map2.put("end", "师傅信息：" + ud.getUserDetail().getName() + " : " + ud.getPhone() + " \\n取消原因：" + (distribution.getRefusedMessage() == null ? "尚未填写取消原因" : distribution.getRefusedMessage()));
        messageService.sendZhaoSf(map2);
        // TODO  取消订单的  原因没有存储            √
        return distributionService.callOff(distribution) > 0;
    }

    /**
     * 申请完成
     *
     * @param distribution
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping("/comple")
    public boolean comple(Distribution distribution, HttpSession session) {

        User ud = userService.selUserByOpenId((String) session.getAttribute("openId"));

        Distribution dis = distributionService.selOrderById(distribution.getId());

        System.out.println(distribution);
        Integer releaseId = distribution.getReleaseId();
        UserRelease userRelease = userReleaseService.selReleaseById(releaseId);
        User user = userService.selById(userRelease.getUserId());

        // 用户收到  师傅发送的申请完成 申请
        Map map = new HashMap();
        map.put("openId", user.getOpenId());
        map.put("template_id", "TF2-OgTgYB6EYKzmno0NjbZobdCadK7U0d0E9O9ZogA");
        map.put("url", "http://java.86blue.cn/_api/goUserOrderDetail?id=" + releaseId);
        map.put("title", ud.getUserDetail().getName() + "师傅申请服务完工，请您点击查看详情确认。");
        map.put("serviceType", userRelease.getTitle());
        map.put("orderNo", dis.getOrderId() + "");
        map.put("orderState", "申请完工");
        map.put("end", ud.getUserDetail().getName() + " : " + ud.getPhone());
        messageService.sendZhaoSf(map);

        map.put("openId", "o5uJY1sfH745I9vnaw06RuhMDRdc");
        map.put("url", "http://java.86blue.cn/_api/goUserOrderDetail?id=" + releaseId);
        map.put("title", "张三师傅申请服务完工，请平台工作人员处理。");
        map.put("serviceType", userRelease.getTitle());
        map.put("orderNo", dis.getOrderId() + "");
        map.put("orderState", "申请完工");
        map.put("end", ud.getUserDetail().getName() + " : " + ud.getPhone());
        messageService.sendZhaoSf(map);

        return distributionService.comple(distribution) > 0;
    }

    /**
     *  用户评论订单   订单完成
     * @param userOrder
     * @param distribution
     * @param disId
     * @return
     */
    @ResponseBody
    @RequestMapping("/comment")
    public boolean comment(UserOrder userOrder, Distribution distribution, Integer disId) {

        Distribution dis = distributionService.selOrderById(disId);

        // 用户下的单
        UserRelease userRelease = userReleaseService.selReleaseById(distribution.getReleaseId());
        // 用户
        User user = userService.selById(userRelease.getUserId());
        // 师傅
        User sf = userService.selByDetailId(userRelease.getReceiveId());
        // 添加评论
        int count = distributionService.comment(userOrder);
        if (count > 0) {
            // 修改派单表
            distribution.setId(disId);
            distributionService.updDistribution(distribution, 7);
            // 修改下单表
            userReleaseService.updReleaseStste(new UserRelease(distribution.getReleaseId(), 6, userRelease.getReceiveId()),7);

            // 平台收到 完工模板
            Map map = new HashMap();
            map.put("openId", "o5uJY1sfH745I9vnaw06RuhMDRdc");
            map.put("template_id", "TF2-OgTgYB6EYKzmno0NjbZobdCadK7U0d0E9O9ZogA");
            map.put("url", "http://java.86blue.cn/_api/goUserOrderDetail?id=" + distribution.getReleaseId());
            map.put("title", "服务已经顺利完工。");
            map.put("serviceType", userRelease.getTitle());
            map.put("orderNo", dis.getOrderId() + "");
            map.put("orderState", "已完工");
            map.put("end", sf.getUserDetail().getName() + " : " + sf.getPhone());
            messageService.sendZhaoSf(map);

            // 用户收到 完工模板
            map.put("openId", user.getOpenId());
            map.put("template_id", "TF2-OgTgYB6EYKzmno0NjbZobdCadK7U0d0E9O9ZogA");
            map.put("url", "http://java.86blue.cn/_api/goUserOrderDetail?id=" + distribution.getReleaseId());
            map.put("title", "服务已经顺利完工。");
            map.put("serviceType", userRelease.getTitle());
            map.put("orderNo", dis.getOrderId() + "");
            map.put("orderState", "已完工");
            map.put("end", sf.getUserDetail().getName() + " : " + sf.getPhone());
            messageService.sendZhaoSf(map);

            // 师傅收到
            map.put("openId", sf.getOpenId());
            map.put("template_id", "TF2-OgTgYB6EYKzmno0NjbZobdCadK7U0d0E9O9ZogA");
            map.put("url", "http://java.86blue.cn/_api/goUserOrderDetail?id=" + distribution.getReleaseId());
            map.put("title", "服务已经顺利完工。");
            map.put("serviceType", userRelease.getTitle());
            map.put("orderNo", dis.getOrderId() + "");
            map.put("orderState", "已完工");
            map.put("end", sf.getUserDetail().getName() + " : " + sf.getPhone());
            messageService.sendZhaoSf(map);
            return true;
        }
        return false;
    }

}
