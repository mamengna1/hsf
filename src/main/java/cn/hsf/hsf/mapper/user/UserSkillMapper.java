package cn.hsf.hsf.mapper.user;

import cn.hsf.hsf.pojo.user.UserSkills;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author kaituozhe
 */
public interface UserSkillMapper {

    List<UserSkills> selAll();

    List<UserSkills> selByParentId(Integer id);

    List<UserSkills> selById(@Param("ids") List<Integer> ids);

}
