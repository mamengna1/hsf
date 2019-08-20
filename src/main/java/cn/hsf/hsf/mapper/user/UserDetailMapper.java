package cn.hsf.hsf.mapper.user;

import cn.hsf.hsf.pojo.user.UserDetail;

public interface UserDetailMapper {

    UserDetail selById(Integer id);

    int insUserDetail(UserDetail userDetail);
}
