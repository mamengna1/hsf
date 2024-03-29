package cn.hsf.hsf.service.user;

import cn.hsf.hsf.commons.WxConstants;
import cn.hsf.hsf.mapper.user.UserInformationMapper;
import cn.hsf.hsf.mapper.user.UserMapper;
import cn.hsf.hsf.mapper.user.UserScoreSourceMapper;
import cn.hsf.hsf.pojo.user.User;
import cn.hsf.hsf.pojo.user.UserInformation;
import cn.hsf.hsf.pojo.user.UserScoreSource;
import cn.hsf.hsf.service.message.MessageService;
import cn.hsf.hsf.utils.Send;
import cn.hsf.hsf.utils.WxUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author kaituozhe
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserScoreSourceMapper userScoreSourceMapper;
    @Autowired
    private UserInformationMapper userInformationMapper;
    @Autowired
    private MessageService messageService;
    @Autowired
    private UserService userService;

    @Override
    public String getUserInfo(String openId) {
        String url = WxConstants.GET_USER_INFO_URL.replace("ACCESS_TOKEN", WxUtil.getAccessToken()).replace("OPENID", openId);
        return Send.get(url);
    }

    @Override
    public int insUser(User user) {
        return userMapper.insUser(user);
    }

    @Override
    public int updUser(User user) {
        int count = userMapper.updUser(user);
        if (count > 0) {
            User ud = userMapper.selByDetailId(user.getDetailId());
            // 发送给平台
            Map map = new HashMap();
            map.put("openId", "o5uJY1sfH745I9vnaw06RuhMDRdc");
            map.put("title", "用户" + ud.getNickName() + "申请注册成为师傅，请尽快审核");
            map.put("serviceType", "注册成为师傅");
            map.put("orderNo", System.currentTimeMillis() + "");
            map.put("orderState", "待审核");
            map.put("end", "师傅信息：" + ud.getUserDetail().getName() + ":" + user.getPhone());
            messageService.sendZhaoSf(map);
        }
        return count;
    }

    /**
     * 查询用户信息
     *
     * @param openId
     * @return
     */
    @Override
    public User selUserByOpenId(String openId) {
        return userMapper.selUserByOpenId(openId);
    }

    /**
     * 用户所有积分的来源
     *
     * @param openId
     * @return
     */
    @Override
    public List<UserScoreSource> selAllByOpeniId(String openId) {
        return userScoreSourceMapper.selAllByOpenId(openId);
    }

    /**
     * 添加积分来源
     *
     * @param userScoreSource
     * @return
     */
    @Override
    public int insScoreSource(UserScoreSource userScoreSource) {
        return userScoreSourceMapper.insScoreSource(userScoreSource);
    }

    /**
     * 查询我的直系下线
     *
     * @param openId
     * @return
     */
    @Override
    public List<User> selMyWorkmate(String openId) {
        return userMapper.selMyWorkmate(openId);
    }

    /**
     * 添加资讯
     *
     * @param userInformation
     * @return
     */
    @Override
    public int insInformation(UserInformation userInformation) {
        return userInformationMapper.insInfomation(userInformation);
    }

    @Override
    public User selById(Integer id) {
        return userMapper.selById(id);
    }

    @Override
    public User selByDetailId(Integer detailId) {
        return userMapper.selByDetailId(detailId);
    }


}
