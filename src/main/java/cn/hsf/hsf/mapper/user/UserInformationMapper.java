package cn.hsf.hsf.mapper.user;

import cn.hsf.hsf.pojo.user.UserInformation;

import java.util.List;

public interface UserInformationMapper {

    int insInfomation(UserInformation userInformation);

    List<UserInformation> selByOpenId(String openId);

}
