package cn.hsf.hsf.service.user;

import cn.hsf.hsf.mapper.user.UserDetailMapper;
import cn.hsf.hsf.mapper.user.UserSkillMapper;
import cn.hsf.hsf.pojo.user.UserDetail;
import cn.hsf.hsf.pojo.user.UserSkill;
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
    public int insUserDetail(UserDetail userDetail) {
        return userDetailMapper.insUserDetail(userDetail);
    }

    @Override
    public List<UserSkill> selAll() {
        return userSkillMapper.selAll();
    }
}
