package cn.hsf.hsf.service.user;

import cn.hsf.hsf.mapper.user.UserReleaseMapper;
import cn.hsf.hsf.pojo.user.UserRelease;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserReleaseServiceImpl implements UserReleaseService {

    @Resource
    private UserReleaseMapper userReleaseMapper;

    @Override
    public int insertUserRelease(UserRelease userRelease) {
        return userReleaseMapper.insertUserRelease(userRelease);
    }
}
