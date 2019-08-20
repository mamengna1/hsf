package cn.hsf.hsf.controller.appmenu;

import cn.hsf.hsf.commons.WxConstants;
import cn.hsf.hsf.pojo.app.AppMenu;
import cn.hsf.hsf.pojo.menu.*;
import cn.hsf.hsf.service.app.WXService;
import cn.hsf.hsf.utils.Send;
import cn.hsf.hsf.utils.WxUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author kaituozhe
 */
@RestController
@RequestMapping("/appmenu")
public class CreateMenuController {

    @Autowired
    private WXService wxService;

    @RequestMapping("/create")
    public boolean create() {

        List<AppMenu> menus = wxService.selAllMenu(0);
        // 创建菜单对象
        Button btn = new Button();
        SubButton sb = null;
        for (AppMenu menu : menus) {
            List<AppMenu> subMenus = wxService.selAllMenu(menu.getId());
            if (subMenus != null && subMenus.size() > 0) {
                sb = new SubButton(menu.getMenuName());
                for (AppMenu subMenu : subMenus) {
                    sb.getSub_button().add(createMenu(subMenu));
                }
                btn.getButton().add(sb);
            } else {
                btn.getButton().add(createMenu(menu));
            }
        }
        // 转为JSON
        JSONObject jsonObject = JSONObject.fromObject(btn);

        String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN".replace("ACCESS_TOKEN", WxUtil.getAccessToken());
        System.out.println(Send.post(url, jsonObject.toString()));
        return true;
    }

    private AbstractButton createMenu(AppMenu menu) {
        // 没有子菜单
        switch (menu.getMenuTypeId()) {
            // 关键字
            case 1:
                return new ClickButton(menu.getMenuName(), menu.getKey());
            // 链接
            case 2:
                return new ViewButton(menu.getMenuName(), menu.getMessage());
            case 3:
                //小程序
                return new ProgramButton(menu.getMenuName(), menu.getMenuName(), WxConstants.APPID, "xx");
            default:
                break;
        }
        return null;
    }

}
