package cn.hsf.hsf.service.sms;

import cn.hsf.hsf.mapper.sms.SendMessageMapper;
import cn.hsf.hsf.mapper.user.UserMapper;
import cn.hsf.hsf.pojo.sms.SendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author kaituozhe
 */
@Service
public class SendMessageServiceImpl implements SendMessageService {

    @Autowired
    private SendMessageMapper sendMessageMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public int insSms(SendMessage sendMessage) {
        return sendMessageMapper.insSms(sendMessage);
    }

    @Override
    public int selByPhone(String phone) {
        return userMapper.selByPhone(phone);
    }

}
