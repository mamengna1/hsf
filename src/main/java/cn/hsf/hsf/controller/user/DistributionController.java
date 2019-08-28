package cn.hsf.hsf.controller.user;

import cn.hsf.hsf.pojo.user.Distribution;
import cn.hsf.hsf.service.user.DistributionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class DistributionController {

    @Autowired
    private DistributionService distributionService;


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

}
