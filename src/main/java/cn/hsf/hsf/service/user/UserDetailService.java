package cn.hsf.hsf.service.user;

import cn.hsf.hsf.pojo.user.UserDetail;
import cn.hsf.hsf.pojo.user.UserSkill;

import java.util.List;

public interface UserDetailService {

    int insUserDetail(UserDetail userDetail);

    List<UserSkill> selAll();
}
