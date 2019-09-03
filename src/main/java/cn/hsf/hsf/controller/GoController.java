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

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @RequestMapping("/goComment")
    public String goComment() {
        return "user/comment";
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
    public String goRegister(Model model, Integer flag) {
        System.out.println("FLAG : " + flag);

        Map<UserSkills, List<UserSkills>> skills = new HashMap<>();

        List<UserSkills> parentSkill = userDetailService.selByParentId(null);
        for (UserSkills skill : parentSkill) {
            skills.put(skill, userDetailService.selByParentId(skill.getId()));
        }
        model.addAttribute("skills",skills);
        model.addAttribute("yearWorks", userDetailService.selYearAll());
        model.addAttribute("flag", flag);
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
        if (openId == null) {
            model.addAttribute("id", -1);
            model.addAttribute("path", "goYongJin");
            // 先去静默授权获取openId
            return "common/getOpenId";
        }
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
     * @param model
     * @param id      师傅ID
     * @param session
     * @return
     */
    @RequestMapping("/goSFHone")
    public String goSFHone(Model model, Integer id, HttpSession session) {

        // session中的openId
        String sesOpenId = (String) session.getAttribute("openId");
        if (sesOpenId == null) {
            model.addAttribute("id", id);
            model.addAttribute("path", "goSFHone");
            // 先去静默授权获取openId
            return "common/getOpenId";
        }
        // 师傅的openId
        String openId = (String) session.getAttribute("openId");
        // 根据师傅的ID查询师傅信息
        if (id != null) {
            openId = userService.selByDetailId(id).getOpenId();
        }

        // 如果是用户进入  是没有师傅的openId的   可以改成传递师傅的id  来获取openId  √
        User user = userService.selUserByOpenId(openId);
        model.addAttribute("user", user);
        model.addAttribute("userDetail", user.getUserDetail());
        // 1 为师傅进入  2 为用户进入   师傅可以发布动态    用户显示  直接雇佣他
        model.addAttribute("flag", "2");
        // 师傅自己   进去首页  显示发布动态
        if (openId.equals(sesOpenId)) {
            model.addAttribute("flag", "1");
        }
        // 技能
        model.addAttribute("skills", userDetailService.selSkillById(Arrays.asList(user.getUserDetail().getSkills().split(","))));
        // 师傅的动态
        // model.addAttribute("infos", userDetailService.selInfoByOpenId(openId));
        return "sf/sfhome";
    }

    @Autowired
    private UserReleaseService userReleaseService;

    /**
     * 去到 师傅订单列表
     * @return
     */
    @RequestMapping("/goOrderList")
    public String goOrderList(Model model, HttpSession session) {
        // TODO 空指针  目前还没找到原因   在测试的时候报 的    可能因为 没有进入过个人中心或者注册页 没有openId   但是好像没有模板跳这个接口  有待检查
        String openId = (String) session.getAttribute("openId");
        if (openId == null) {
            model.addAttribute("id", -1);
            model.addAttribute("path", "goSFHone");
            // 先去静默授权获取openId
            return "common/getOpenId";
        }
        User user = userService.selUserByOpenId(openId);
        // 师傅的订单
        if (user.getDetailId() != null && user.getDetailId() != 0 && user.getUserDetail().getStatus() == 1) {
            List<Distribution> uid = distributionService.selAllOrderBySfId(user.getDetailId());
            model.addAttribute("list", uid);
            // 1 代表师傅
            model.addAttribute("isSf", 1);
            return "user/orderlist";
        } else { // 用户的订单
            model.addAttribute("orders", userReleaseService.selAllByUserId((Integer) session.getAttribute("uid")));
            // 2 代表用户
            model.addAttribute("isSf", 2);
            return "user/orderlist";
        }
    }

    @RequestMapping("/goSfYuYue")
    public String goSfYuYue(Model model, HttpSession session) {
        model.addAttribute("isSf", 1);
        model.addAttribute("orders", userReleaseService.selAllByUserId((Integer) session.getAttribute("uid")));
        return "user/orderlist";
    }


    /**
     * 派单ID
     * @param id
     * @param model
     * @param session
     * @return
     */
    @RequestMapping("/goOrderShow")
    public String goOrderShow(Integer id, Model model, HttpSession session) {
        System.out.println("进来了  啊啊 啊啊啊");
        String openId = (String) session.getAttribute("openId");

        if (openId == null) {
            model.addAttribute("id", id);
            model.addAttribute("path", "goOrderShow");
            // 先去静默授权获取openId
            return "common/getOpenId";
        }
        System.out.println("派单ID ： " + id);
        model.addAttribute("order", distributionService.selOrderById(id));
        model.addAttribute("user", userService.selUserByOpenId(openId));
        return "user/ordershow";
    }

    /**
     * 传入的是下单ID
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/goUserOrderDetail")
    public String goUserOrderDetail(Integer id, Model model) {
        UserRelease userRelease = userReleaseService.selReleaseById(id);
        // 是否有师傅接单
        if (userRelease.getReceiveId() == null) {
            // 跟这个师傅的订单  需要显示信息
            model.addAttribute("userOrder", userReleaseService.selReleaseById(id));
            return "user/usershow";
        } else {
            // 传递 派单ID
            Distribution paidan = distributionService.sel(userRelease);
            return "redirect:/goOrderShow?id=" + paidan.getId();
        }
    }

    @RequestMapping("/backOpenId")
    public String backOpenId(HttpSession session, Integer id, String path, Model model) {
        String openId = (String) session.getAttribute("openId");
        model.addAttribute("id", id);
        model.addAttribute("path", path);
        // 先去静默授权获取openId
        return "common/getOpenId";
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
        btn.getButton().add(new ViewButton("成为师傅", "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx8bf85bd98eaddb86&redirect_uri=http://java.86blue.cn/_api/GetUserInfoController?path=goRegister&response_type=code&scope=snsapi_userinfo#wechat_redirect"));
        // 第二个一级菜单
        btn.getButton().add(new ViewButton("个人中心", "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx8bf85bd98eaddb86&redirect_uri=http://java.86blue.cn/_api/GetUserInfoController?path=goIndex&response_type=code&scope=snsapi_userinfo#wechat_redirect"));
        // 创建第三个一级菜单
        SubButton sb = new SubButton("订单");
        // 为第三个一级菜单增加子菜单
        sb.getSub_button().add(new ViewButton("首页", "http://java.86blue.cn/_api/goSFList"));
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
