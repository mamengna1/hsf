package cn.hsf.hsf.service.sms;

import cn.hsf.hsf.pojo.sms.SendMessage;

public interface SendMessageService {

    int insSms(SendMessage sendMessage);

    /**
     *  根据手机号查询  是否 已近绑定用户
     * @param phone
     * @return
     */
    int selByPhone(String phone);
}
