package cn.hsf.hsf.mapper.user;

import cn.hsf.hsf.pojo.user.UserRelease;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserReleaseMapper {

    UserRelease selReleaseById(Integer id);

    //插入
    int insertUserRelease(UserRelease userRelease);

    /**
     *  师傅接单修改状态
     * @param userRelease
     * @return
     */
    int updUserRelease(@Param("ur") UserRelease userRelease, @Param("oldState") Integer oldState);


    /**
     *  查询用户的所有订单
     * @param userId
     * @return
     */
    List<UserRelease>  selAllByUserId(Integer userId);

}
