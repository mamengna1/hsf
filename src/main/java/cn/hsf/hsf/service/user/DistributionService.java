package cn.hsf.hsf.service.user;


import cn.hsf.hsf.pojo.user.Distribution;

import java.util.List;

public interface DistributionService {

    /**
     * 师傅所有订单
     * @param sfId
     * @return
     */
    List<Distribution> selAllOrderBySfId(Integer sfId);

    /**
     *  查询指定订单
     * @param id
     * @return
     */
    Distribution selOrderById(Integer id);


}
