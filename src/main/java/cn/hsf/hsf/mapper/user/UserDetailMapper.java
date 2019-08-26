package cn.hsf.hsf.mapper.user;

import cn.hsf.hsf.pojo.user.UserDetail;
import cn.hsf.hsf.pojo.user.UserYearWork;

import java.util.List;

public interface UserDetailMapper {

    UserDetail selById(Integer id);

    int insUserDetail(UserDetail userDetail);

    int updUserLineStatus(UserDetail userDetail);

    List<UserYearWork> selYearAll();

    UserYearWork selYearById(Integer id);
}
