package cn.hsf.hsf.service.user;

import cn.hsf.hsf.pojo.user.User;

public interface UserService {

    /**
     *  获取用户信息（微信接口）
     * @param openId
     * @return
     */
    String getUserInfo(String openId);

    /**
     *  新增用户信息
     * @param user
     * @return
     */
    int insUser(User user);

    /**
     *  修改用户信息
     * @param user
     * @return
     */
    int updUser(User user);

    /**
     *  根据openId查询用户信息
     * @param openId
     * @return
     */
    User selUserByOpenId(String openId);



}
