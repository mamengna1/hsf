package cn.hsf.hsf.service.user;

import cn.hsf.hsf.mapper.user.UserDetailMapper;
import cn.hsf.hsf.mapper.user.UserInformationMapper;
import cn.hsf.hsf.mapper.user.UserSkillMapper;
import cn.hsf.hsf.pojo.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author kaituozhe
 */
@Service
public class UserDetailServiceImpl implements UserDetailService {

    @Autowired
    private UserDetailMapper userDetailMapper;
    @Autowired
    private UserSkillMapper userSkillMapper;
    @Autowired
    private UserInformationMapper userInformationMapper;


    @Override
    public UserDetail selById(Integer id) {
        return userDetailMapper.selById(id);
    }

    /**
     * 师傅注册添加
     * @param userDetail
     * @return
     */
    @Override
    public int insUserDetail(UserDetail userDetail) {
        System.out.println("添加师傅");
        return userDetailMapper.insUserDetail(userDetail);
    }

    /**
     * 查询所有技能
     * @return
     */
    @Override
    public List<UserSkills> selAll() {
        return userSkillMapper.selAll();
    }
    /**
     *  根据父id查询
     * @return
     */
    @Override
    public List<UserSkills> selByParentId(Integer id){
        return userSkillMapper.selByParentId(id);
    }

    /**
     * 修改师傅在线状态
     * @param userDetail
     * @return
     */
    @Override
    public int updUserLineStatus(UserDetail userDetail) {
        return userDetailMapper.updUserLineStatus(userDetail);
    }

    /**
     * 查询所有工作年限
     * @return
     */
    @Override
    public List<UserYearWork> selYearAll() {
        return userDetailMapper.selYearAll();
    }

    @Override
    public List<UserSkills> selSkillById(List ids) {
        return userSkillMapper.selById(ids);
    }

    /**
     * 查看师傅对应的所有动态
     * @param openId
     * @return
     */
    @Override
    public List<UserInformation> selInfoByOpenId(String openId) {
        return userInformationMapper.selByOpenId(openId);
    }

    @Override
    public List<UserDetail> selBySkill(UserDetail userDetail) {
        return userDetailMapper.selBySkill(userDetail);
    }

    @Override
    public int delDynamic(Integer id) {
        return userInformationMapper.delDynamic(id);
    }
}
