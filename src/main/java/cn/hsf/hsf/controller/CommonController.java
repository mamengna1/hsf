package cn.hsf.hsf.controller;

import cn.hsf.hsf.commons.ImageURL;
import cn.hsf.hsf.commons.WxConstants;
import cn.hsf.hsf.mapper.app.AppMapper;
import cn.hsf.hsf.pojo.Result;
import cn.hsf.hsf.pojo.app.App;
import cn.hsf.hsf.pojo.user.User;
import cn.hsf.hsf.service.event.EventService;
import cn.hsf.hsf.service.user.UserService;
import cn.hsf.hsf.utils.Send;
import cn.hsf.hsf.utils.WxUtil;
import com.alibaba.fastjson.JSON;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@Controller
public class CommonController {

    @Autowired
    private UserService userService;
    @Autowired
    private EventService eventService;
    @Autowired
    private AppMapper appMapper;

    @ResponseBody
    @RequestMapping("/getUserInfo")
    public User getOpenId(String code, HttpSession session) {
        System.out.println("CODE : " + code);
        String url = WxConstants.GET_ACCESS_TOKEN_URL.replace("APPID", WxConstants.APPID).replace("SECRET", WxConstants.APPSECRET).replace("CODE", code);
        String result = Send.get(url);
        String openid = JSONObject.fromObject(result).getString("openid");
        session.setAttribute("openId", openid);

        return userService.selUserByOpenId(openid);
    }

    /**
     * 获取用户信息
     *
     * @param req
     * @param resp
     */
    @RequestMapping("/GetUserInfoController")
    public void getUserInfo(HttpServletRequest req, HttpServletResponse resp) {

        App app = appMapper.selapp();

        // 获取code
        String code = req.getParameter("code");
        System.out.println("CODE:" + code);
        //换取accesstoken的地址
        String url = WxConstants.GET_ACCESS_TOKEN_URL.replace("APPID", app.getAppId()).replace("SECRET", app.getAppSecret()).replace("CODE", code);
        String result = Send.get(url);

        String at = JSONObject.fromObject(result).getString("access_token");
        String openid = JSONObject.fromObject(result).getString("openid");

        //拉取用户的基本信息
        url = WxConstants.GET_BASE_USER_INFO_URL.replace("ACCESS_TOKEN", at).replace("OPENID", openid);
        result = Send.get(url);

        Map res = JSON.parseObject(result);
        res.put("isSub", 1);

        User user = userService.selUserByOpenId(openid);
        if (user == null) {
            user = new User((String) res.get("nickname"), openid, (Integer) res.get("sex"), (String) res.get("headimgurl"), (String) res.get("country"),
                    (String) res.get("province"), (String) res.get("city"));
            userService.insUser(user);
        } else {
            // userService.updUser(user);
        }

        try {
            req.getSession().setAttribute("openId", openid);
            req.getSession().setAttribute("timestacp", System.currentTimeMillis());
            req.getSession().setAttribute("uid", user.getId());
            req.getSession().setAttribute("detailId", user.getDetailId());
            resp.sendRedirect("/_api/goIndex");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/retOpenId")
    public void retOpenId() {
        Send.get("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx8bf85bd98eaddb86&redirect_uri=http://java.86blue.cn/_api/getUserInfo&response_type=code&scope=snsapi_base&state=1#wechat_redirect");
    }

    @ResponseBody
    @RequestMapping("/getEWM")
    public Result getEWM(HttpSession session) {
        System.out.println("进来了");
        return new Result(ImageURL.GET_USER_EWM_URL + session.getAttribute("uid"));
    }
}
