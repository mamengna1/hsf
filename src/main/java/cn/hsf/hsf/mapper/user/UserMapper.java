package cn.hsf.hsf.mapper.user;

import cn.hsf.hsf.pojo.user.User;

import java.util.List;

/**
 * @author kaituozhe
 */
public interface UserMapper {

    User selUserByOpenId(String openId);

    int insUser(User user);

    int updUser(User user);

    int selByPhone(String phone);

    List<User> selMyWorkmate(String openId);

    User selById(Integer id);

    User selByDetailId(Integer detailId);
}
