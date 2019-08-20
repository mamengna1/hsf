package cn.hsf.hsf.service.back;

import cn.hsf.hsf.pojo.back.CashBack;

import java.util.List;

public interface CashBackService {

    /**
     * 根据openId查询所有提现记录
     * @param openId
     * @return
     */
    List<CashBack> selAllByOpenId(String openId);

    /**
     *  添加提现记录
     * @param cashBack
     * @return
     */
    int insRecord(CashBack cashBack);


}
