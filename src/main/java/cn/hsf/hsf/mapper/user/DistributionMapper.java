package cn.hsf.hsf.mapper.user;

import cn.hsf.hsf.pojo.user.Distribution;
import cn.hsf.hsf.pojo.user.DistributionStatus;

import java.util.List;

public interface DistributionMapper {

    /**
     * 订单状态
     * @param id
     * @return
     */
    DistributionStatus selStatusById(Integer id);

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

    /**
     *  判断是否接过单
     * @param releaseId
     * @return
     */
    int selReleaseById(Integer releaseId);

    /**
     *  接过单后修改信息
     * @param distribution
     * @return
     */
    int updDistribution(Distribution distribution);



}
