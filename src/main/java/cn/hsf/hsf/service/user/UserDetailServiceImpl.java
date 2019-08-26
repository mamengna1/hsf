package cn.hsf.hsf.service.user;

import cn.hsf.hsf.mapper.user.UserDetailMapper;
import cn.hsf.hsf.mapper.user.UserSkillMapper;
import cn.hsf.hsf.pojo.user.UserDetail;
import cn.hsf.hsf.pojo.user.UserSkill;
import cn.hsf.hsf.pojo.user.UserYearWork;
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

    @Override
    public UserDetail selById(Integer id) {
        return userDetailMapper.selById(id);
    }

    /**
     *  师傅注册添加
     * @param userDetail
     * @return
     */
    @Override
    public int insUserDetail(UserDetail userDetail) {
        return userDetailMapper.insUserDetail(userDetail);
    }

    /**
     *  查询所有技能
     * @return
     */
    @Override
    public List<UserSkill> selAll() {
        return userSkillMapper.selAll();
    }

    /**
     *  修改师傅在线状态
     * @param userDetail
     * @return
     */
    @Override
    public int updUserLineStatus(UserDetail userDetail) {
        return userDetailMapper.updUserLineStatus(userDetail);
    }

    /**
     *  查询所有工作年限
     * @return
     */
    @Override
    public List<UserYearWork> selYearAll() {
        return userDetailMapper.selYearAll();
    }

    @Override
    public List<UserSkill> selSkillById(List ids) {
        return userSkillMapper.selById(ids);
    }
}
