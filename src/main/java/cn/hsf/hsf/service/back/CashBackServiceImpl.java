package cn.hsf.hsf.service.back;

import cn.hsf.hsf.mapper.back.CashBackMapper;
import cn.hsf.hsf.pojo.back.CashBack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author kaituozhe
 */
@Service
public class CashBackServiceImpl implements CashBackService {

    @Autowired
    private CashBackMapper cashBackMapper;
    /**
     * 根据openId查询所有提现记录
     * @param openId
     * @return
     */
    @Override
    public List<CashBack> selAllByOpenId(String openId) {
        return cashBackMapper.selAllByOpenId(openId);
    }

    /**
     *  添加提现记录
     * @param cashBack
     * @return
     */
    @Override
    public int insRecord(CashBack cashBack) {
        return cashBackMapper.insRecord(cashBack);
    }
}
