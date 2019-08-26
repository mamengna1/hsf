package cn.hsf.hsf.service.user;

import cn.hsf.hsf.pojo.user.UserDetail;
import cn.hsf.hsf.pojo.user.UserInformation;
import cn.hsf.hsf.pojo.user.UserSkill;
import cn.hsf.hsf.pojo.user.UserYearWork;

import java.util.List;

public interface UserDetailService {

    UserDetail selById(Integer id);

    int insUserDetail(UserDetail userDetail);

    List<UserSkill> selAll();

    int updUserLineStatus(UserDetail userDetail);

    List<UserYearWork> selYearAll();

    /**
     *  查询师傅所有的技能
     * @param ids
     * @return
     */
    List<UserSkill> selSkillById(List ids);

    List<UserInformation> selInfoByOpenId(String openId);

    List<UserDetail> selBySkill(UserDetail userDetail);
}
