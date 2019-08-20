package cn.hsf.hsf.controller;

import cn.hsf.hsf.commons.WxConstants;
import cn.hsf.hsf.pojo.menu.*;
import cn.hsf.hsf.pojo.user.User;
import cn.hsf.hsf.pojo.user.UserSkill;
import cn.hsf.hsf.service.back.CashBackService;
import cn.hsf.hsf.service.user.UserDetailServiceImpl;
import cn.hsf.hsf.service.user.UserService;
import cn.hsf.hsf.utils.Send;
import cn.hsf.hsf.utils.WxUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

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

    @RequestMapping("/goIndex")
    public String goIndex(Model model, HttpSession session) {
        User user = userService.selUserByOpenId((String) session.getAttribute("openId"));
        System.out.println("返回前端的数据" + user);
        model.addAttribute("user", user);
        return "index";
    }


    @RequestMapping("/user")
    public String goUser() {
        return "user/user.html";
    }

    @RequestMapping("/goRegister")
    public String goRegister(Model model) {
        model.addAttribute("skills", userDetailService.selAll());
        return "register";
    }

    @RequestMapping("/goBindPhone")
    public String goBindPhone(HttpSession session, Model model) {
        String phone = userService.selUserByOpenId((String) session.getAttribute("openId")).getPhone();
        // "$1****$2"   $1 ：正则中的第一个\\d{3} $2 ：正则中的第二个
        session.setAttribute("phone", phone != null ? phone.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2") : phone);
        return "bindingphone";
    }

    /**
     * 去 二维码页面
     *
     * @return
     */
    @RequestMapping("/goMyEWN")
    public String goMyEWN() {
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
    public String goBackList(Model model, HttpSession session){
        model.addAttribute("list", cashBackService.selAllByOpenId((String) session.getAttribute("openId")));
        return "backList";
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
