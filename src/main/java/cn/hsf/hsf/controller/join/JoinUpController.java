package cn.hsf.hsf.controller.join;

import cn.hsf.hsf.commons.WxConstants;
import cn.hsf.hsf.mapper.app.AppMapper;
import cn.hsf.hsf.pojo.app.App;
import cn.hsf.hsf.pojo.app.AppMenu;
import cn.hsf.hsf.service.app.WXService;
import cn.hsf.hsf.service.event.EventService;
import cn.hsf.hsf.utils.Send;
import cn.hsf.hsf.utils.WxUtil;
import com.alibaba.fastjson.JSON;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * 对接微信公众号   接入
 *
 * @author kaituozhe
 */
@RestController
public class JoinUpController {

    @Autowired
    private WXService wxService;
    @Autowired
    private EventService eventService;


    @GetMapping("/wx")
    public void joinWX(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // 1、 开发者在公众号中提交修改信息后，微信服务器讲发送GET请求到前些的服务器地址URL上

        // 2、 验证消息 的确 来自微信服务器
        // 微信加密签名， signature结合了开发者填写的token参数和请求中的timestamp参数，nonce参数
        String signature = req.getParameter("signature");
        // 时间戳
        String timestamp = req.getParameter("timestamp");
        // 随机数
        String nonce = req.getParameter("nonce");
        // 随机字符串
        String echostr = req.getParameter("echostr");
        if (wxService.check(timestamp, nonce, signature)) {
            System.out.println("接入成功");
            PrintWriter out = resp.getWriter();
            //若确认此次GET请求来自微信服务器，请原样返回echostr参数内容
            out.print(echostr);
            out.flush();
        } else {
            System.out.println("接入失败");
        }

    }

    @PostMapping("/wx")
    public void manager(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        // 解析XML数据
        Map<String, String> requestMap = WxUtil.parseRequest(req.getInputStream());
        requestMap.forEach((k, v) -> System.out.println(k + "  :  " + v));

        String respXml = wxService.getResponse(requestMap);

        PrintWriter out = resp.getWriter();
        out.print(respXml);
        out.flush();
        out.close();
    }




}
