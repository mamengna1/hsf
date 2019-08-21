package cn.hsf.hsf.mapper.user;

import cn.hsf.hsf.pojo.user.UserScoreSource;

import java.util.List;

public interface UserScoreSourceMapper {

    List<UserScoreSource> selAllByOpenId(String openId);

    int insScoreSource(UserScoreSource userScoreSource);

}
