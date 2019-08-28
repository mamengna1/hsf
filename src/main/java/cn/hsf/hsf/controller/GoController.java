package cn.hsf.hsf.controller;

import cn.hsf.hsf.commons.ImageURL;
import cn.hsf.hsf.commons.WxConstants;
import cn.hsf.hsf.pojo.Result;
import cn.hsf.hsf.pojo.menu.*;
import cn.hsf.hsf.pojo.menu.Button;
import cn.hsf.hsf.pojo.user.*;
import cn.hsf.hsf.service.back.CashBackService;
import cn.hsf.hsf.service.user.DistributionService;
import cn.hsf.hsf.service.user.UserDetailServiceImpl;
import cn.hsf.hsf.service.user.UserReleaseService;
import cn.hsf.hsf.service.user.UserService;
import cn.hsf.hsf.utils.Send;
import cn.hsf.hsf.utils.WxUtil;
import com.sun.org.apache.xpath.internal.operations.Mod;
import net.sf.json.JSONObject;
import org.hibernate.validator.constraints.pl.REGON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @author kaituozhe
 */
@Controller
public class GoController {

    @Autowired
    private UserDetailServiceImpl userDetailService;
    @Autowired
    private UserService userService;
    @Autowired
    private CashBackService cashBackService;
    @Autowired
    private DistributionService distributionService;

    @RequestMapping("/goIndex")
    public String goIndex(Model model, HttpSession session) {
        User user = userService.selUserByOpenId((String) session.getAttribute("openId"));
        model.addAttribute("user", user);
        return "index";
    }

    @RequestMapping("/goAwait")
    public String goAwait() {
        return "await";
    }


    @RequestMapping("/user")
    public String goUser() {
        return "user/user.html";
    }

    /**
     * @param model
     * @param flag  true表示从个人中心点击
     * @return
     */
    @RequestMapping("/goRegister")
    public String goRegister(Model model, boolean flag) {
        model.addAttribute("skills", userDetailService.selAll());
        model.addAttribute("yearWorks", userDetailService.selYearAll());
        return "register";
    }

    @RequestMapping("/goBindPhone")
    public String goBindPhone() throws IOException {
        return "bindingphone";
    }

    @ResponseBody
    @RequestMapping("/getPhone")
    public Result getPhone(HttpSession session) {
        String phone = userService.selUserByOpenId((String) session.getAttribute("openId")).getPhone();
        return new Result(phone != null ? phone.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2") : phone, true);
    }

    /**
     * 去 二维码页面
     *
     * @return
     */
    @RequestMapping("/goMyEWN")
    public String goMyEWN(Model model, HttpSession session) {
        model.addAttribute("ewm", ImageURL.GET_USER_EWM_URL + session.getAttribute("uid") + ".jpg");
        return "myewm";
    }

    /**
     * 根据openId查出  佣金和 可体现金额  去到  佣金页
     *
     * @param session
     * @return
     */
    @RequestMapping("/goYongJin")
    public String backMoney(HttpSession session, Model model) {
        String openId = (String) session.getAttribute("openId");
        User user = userService.selUserByOpenId(openId);
        model.addAttribute("totalScore", user.getTotalScore());
        model.addAttribute("balanceScore", user.getBalanceScore());
        return "yongjin";
    }

    @RequestMapping("/goBackList")
    public String goBackList(Model model, HttpSession session) {
        model.addAttribute("list", cashBackService.selAllByOpenId((String) session.getAttribute("openId")));
        return "backList";
    }

    @RequestMapping("/goSource")
    public String goSource(Model model, HttpSession session) {
        model.addAttribute("list", userService.selAllByOpeniId((String) session.getAttribute("openId")));
        return "sourceList";
    }

    /**
     * 去到发布动态
     *
     * @return
     */
    @RequestMapping("/goMyDynamic")
    public String goMyDynamic() {
        return "myDynamic";
    }

    @RequestMapping("/goMyWorkmate")
    public String goMyWorkmate(Model model, HttpSession session) {
        List<User> list = userService.selMyWorkmate((String) session.getAttribute("openId"));
        model.addAttribute("list", list);
        model.addAttribute("len", list.size());
        return "myWorkmate";
    }

    /**
     * 去到师傅个人主页
     *
     * @return
     */
    @RequestMapping("/goSFHone")
    public String goSFHone(Model model, HttpSession session) {
        User user = userService.selUserByOpenId((String) session.getAttribute("openId"));
        model.addAttribute("user", user);
        model.addAttribute("userDetail", user.getUserDetail());
        model.addAttribute("flag", "1");
        model.addAttribute("skills", userDetailService.selSkillById(Arrays.asList(user.getUserDetail().getSkills().split(","))));
        model.addAttribute("infos", userDetailService.selInfoByOpenId((String) session.getAttribute("openId")));
        return "sf/sfhome";
    }

    @Autowired
    private UserReleaseService userReleaseService;

    /**
     * 去到 师傅订单列表
     *
     * @return
     */
    @RequestMapping("/goOrderList")
    public String goOrderList(Model model, HttpSession session) {
        Integer userDetailId = userService.selUserByOpenId((String) session.getAttribute("openId")).getDetailId();
        // 师傅的订单
        if (userDetailId != null && userDetailId != 0) {
            List<Distribution> uid = distributionService.selAllOrderBySfId(userDetailId);
            model.addAttribute("list", uid);
            return "user/orderlist";
        } else { // 用户的订单
            model.addAttribute("orders", userReleaseService.selAllByUserId((Integer) session.getAttribute("uid")));
            return "user/userList";
        }
    }

    @RequestMapping("/goOrderShow")
    public String goOrderShow(Integer id, Model model, HttpSession session) {
        System.out.println( "派单ID ： "+id);
        model.addAttribute("order", distributionService.selOrderById(id));
        model.addAttribute("user", userService.selUserByOpenId((String) session.getAttribute("openId")));
        return "user/ordershow";
    }

    @RequestMapping("/goUserOrderDetail")
    public String goUserOrderDetail(Integer id, Model model) {
        UserRelease userRelease = userReleaseService.selReleaseById(id);
        // 是否有师傅接单
        if (userRelease.getReceiveId() == null) {
            // 跟这个师傅的订单  需要显示信息
            model.addAttribute("userOrder", userReleaseService.selReleaseById(id));
            return "user/usershow";
        } else {
            Distribution sf = distributionService.sel(userRelease);
            return "redirect:/goOrderShow?id=" + sf.getId();
        }
    }


    @RequestMapping("/goTemp")
    public String goTemp() {
        return "temp";
    }

    @ResponseBody
    @RequestMapping("/createMenu")
    public String create() {
        System.out.println("2222");
        // 菜单对象
        Button btn = new Button();
        // 第一个一级菜单
        btn.getButton().add(new ViewButton("成为师傅", "http://java.86blue.cn/_api/goRegister"));
        // 第二个一级菜单
        btn.getButton().add(new ViewButton("个人中心", "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx8bf85bd98eaddb86&redirect_uri=http://java.86blue.cn/_api/GetUserInfoController&response_type=code&scope=snsapi_userinfo#wechat_redirect"));
        // 创建第三个一级菜单
        SubButton sb = new SubButton("订单");
        // 为第三个一级菜单增加子菜单
        sb.getSub_button().add(new ViewButton("订单查看", "http://java.86blue.cn/_api/goTemp"));
        sb.getSub_button().add(new PhotoOrAlbumButton("传图", "31"));
        sb.getSub_button().add(new ClickButton("点击", "32"));
        sb.getSub_button().add(new ViewButton("网易新闻", "http://java.86blue.cn/_api/goRegister"));
        // 加入第三个一级菜单
        btn.getButton().add(sb);

        // 转为JSON
        JSONObject jsonObject = JSONObject.fromObject(btn);

        String url = WxConstants.CREATE_MENU_URL.replace("ACCESS_TOKEN", WxUtil.getAccessToken());
        System.out.println("成功");
        return Send.post(url, jsonObject.toString());
    }


}
