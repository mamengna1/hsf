package cn.hsf.hsf.service.user;

import cn.hsf.hsf.pojo.user.UserRelease;

import java.util.List;

public interface UserReleaseService {

    //插入
    int insertUserRelease(UserRelease userRelease);

    List<UserRelease> selAllByUserId(Integer userId);

    UserRelease selReleaseById(Integer id);

    /**
     *  修改下单状态
     * @param userRelease
     * @param oldState
     * @return
     */
    int updReleaseStste(UserRelease userRelease, Integer oldState);
}
