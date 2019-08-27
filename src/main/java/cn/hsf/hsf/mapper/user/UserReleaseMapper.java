package cn.hsf.hsf.mapper.user;

import cn.hsf.hsf.pojo.user.UserRelease;

public interface UserReleaseMapper {

    UserRelease selReleaseById(Integer id);

    //插入
    int insertUserRelease(UserRelease userRelease);


}
