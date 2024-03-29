package cn.hsf.hsf.controller.image;

import cn.hsf.hsf.commons.ImageURL;
import cn.hsf.hsf.commons.WxConstants;
import cn.hsf.hsf.pojo.Result;
import cn.hsf.hsf.pojo.user.User;
import cn.hsf.hsf.service.user.UserService;
import cn.hsf.hsf.utils.ImageUtil;
import cn.hsf.hsf.utils.Send;
import cn.hsf.hsf.utils.Sign;
import cn.hsf.hsf.utils.WxUtil;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author kaituozhe
 */
@RestController
public class ImageController {

    @Autowired
    private UserService userService;


    @RequestMapping("/getImage")
    public void getImage(HttpServletResponse resp, String path) throws Exception {

        String url = WxConstants.GET_JSAPI_TICKET.replace("ACCESS_TOKEN", WxUtil.getAccessToken());

        String resultTicket = Send.get(url);
        Map ticket = JSON.parseObject(resultTicket);

        Map<String, String> respMap = Sign.sign((String) ticket.get("ticket"), path);

        respMap.forEach((k, v) -> System.out.println(k + "  :  " + v));

        PrintWriter os = resp.getWriter();
        String result = JSON.toJSONString(respMap);
        System.out.println(result);
        os.print(result);
        os.flush();
        os.close();
    }

    @GetMapping("/saveImage")
    public Result saveImage(HttpServletRequest request, String url) {
        System.out.println("==========================================================================================");
        String fileName;
        try{
            fileName = WxUtil.saveImageToDisk(url, ImageURL.USER_CARD_URL);
        }catch (Exception e){
            return new Result(null, false);
        }
        return new Result(ImageURL.READ_USER_CARD_URL + fileName, true);
    }

    @GetMapping("/savePoster")
    public String savePoster(String url, HttpSession session) {
        String fileName = WxUtil.saveImageToDisk(url);

        if (StringUtils.isEmpty(fileName)) {
            return "false";
        }
        User user = userService.selUserByOpenId((String) session.getAttribute("openId"));
        String file = ImageURL.GET_POSTERS_URL + ImageUtil.saveImage(ImageURL.GET_BACK_IMG_URL + fileName, ImageURL.GET_USER_EWM_URL + session.getAttribute("uid") + ".jpg", user.getId() + ".jpg", user.getNickName());
        System.out.println("海报保存路径： " + file);
        return file;
    }

    @ResponseBody
    @RequestMapping("/savePoster_2")
    public Result savePoster_2(String path, HttpSession session){
        User user = userService.selUserByOpenId((String) session.getAttribute("openId"));
        String file = ImageURL.GET_POSTERS_URL + ImageUtil.saveImage(path, ImageURL.GET_USER_EWM_URL + session.getAttribute("uid") + ".jpg", user.getId() + ".jpg", user.getNickName());
        System.out.println("海报保存路径： " + file);
        return new Result(file);
    }
}
