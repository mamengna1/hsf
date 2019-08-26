package cn.hsf.hsf.mapper.user;

import cn.hsf.hsf.pojo.user.UserSkill;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author kaituozhe
 */
public interface UserSkillMapper {

    List<UserSkill> selAll();

    List<UserSkill> selById(@Param("ids") List<Integer> ids);

}
