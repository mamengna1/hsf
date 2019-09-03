package cn.hsf.hsf.service.user;

import cn.hsf.hsf.mapper.user.UserReleaseMapper;
import cn.hsf.hsf.pojo.user.UserRelease;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserReleaseServiceImpl implements UserReleaseService {

    @Resource
    private UserReleaseMapper userReleaseMapper;

    @Override
    public int insertUserRelease(UserRelease userRelease) {
        return userReleaseMapper.insertUserRelease(userRelease);
    }

    @Override
    public List<UserRelease> selAllByUserId(Integer userId) {
        return userReleaseMapper.selAllByUserId(userId);
    }

    /**
     * 用户查看订单详情
     *
     * @param id
     * @return
     */
    @Override
    public UserRelease selReleaseById(Integer id) {
        return userReleaseMapper.selReleaseById(id);
    }

    /**
     * 修改下单状态
     * @param userRelease
     * @param oldState
     * @return
     */
    @Override
    public int updReleaseStste(UserRelease userRelease, Integer oldState) {
        return userReleaseMapper.updUserRelease(userRelease, oldState);
    }
}
