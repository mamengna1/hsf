package cn.hsf.hsf.mapper.user;

import cn.hsf.hsf.pojo.user.UserRelease;

import java.util.List;

public interface UserReleaseMapper {

    UserRelease selReleaseById(Integer id);

    //插入
    int insertUserRelease(UserRelease userRelease);

    /**
     *  师傅接单修改状态
     * @param userRelease
     * @return
     */
    int updUserRelease(UserRelease userRelease);

    /**
     *  查询用户的所有订单
     * @param userId
     * @return
     */
    List<UserRelease>  selAllByUserId(Integer userId);

}
