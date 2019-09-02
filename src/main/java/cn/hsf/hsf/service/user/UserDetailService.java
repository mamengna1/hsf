package cn.hsf.hsf.service.user;

import cn.hsf.hsf.pojo.user.UserDetail;
import cn.hsf.hsf.pojo.user.UserInformation;
import cn.hsf.hsf.pojo.user.UserSkills;
import cn.hsf.hsf.pojo.user.UserYearWork;

import java.util.List;

public interface UserDetailService {

    UserDetail selById(Integer id);

    int insUserDetail(UserDetail userDetail);

    List<UserSkills> selAll();
    List<UserSkills> selByParentId(Integer id);

    int updUserLineStatus(UserDetail userDetail);

    List<UserYearWork> selYearAll();

    /**
     *  查询师傅所有的技能
     * @param ids
     * @return
     */
    List<UserSkills> selSkillById(List ids);

    List<UserInformation> selInfoByOpenId(String openId);

    List<UserDetail> selBySkill(UserDetail userDetail);

    int delDynamic(Integer id);
}
