package cn.hsf.hsf.service.user;

import cn.hsf.hsf.commons.WxConstants;
import cn.hsf.hsf.mapper.user.UserMapper;
import cn.hsf.hsf.pojo.user.User;
import cn.hsf.hsf.utils.Send;
import cn.hsf.hsf.utils.WxUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author kaituozhe
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

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
        return userMapper.updUser(user);
    }

    @Override
    public User selUserByOpenId(String openId) {
        return userMapper.selUserByOpenId(openId);
    }


}
