package cn.hsf.hsf.mapper.app;

import cn.hsf.hsf.pojo.app.AppMenu;

import java.util.List;

public interface AppMenuMapper {

    List<AppMenu> selAll(Integer id);

    /**
     *  根据key查询  按钮要回复的信息
     * @param key
     * @return
     */
    AppMenu selByKey(String key);
}
