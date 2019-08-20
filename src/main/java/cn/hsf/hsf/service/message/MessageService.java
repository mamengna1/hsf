package cn.hsf.hsf.service.message;

import cn.hsf.hsf.pojo.user.User;

import java.util.Map;

/**
 * @author Administrator
 */
public interface MessageService {

    void sendTemplateMessage(Map<String, String> map);

    void sendSubTemplateMessage(User user, boolean flag);

    void sendSalesTemplateMessage(Map<String, String> map);

}
