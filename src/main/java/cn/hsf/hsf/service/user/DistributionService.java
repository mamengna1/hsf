package cn.hsf.hsf.service.user;


import cn.hsf.hsf.pojo.user.Distribution;
import cn.hsf.hsf.pojo.user.UserOrder;
import cn.hsf.hsf.pojo.user.UserRelease;

import javax.servlet.http.HttpSession;
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

    /**
     *  判断是否接过单
     * @param releaseId
     * @return
     */
    int selReleaseById(Integer releaseId);

    /**
     *  开启事务接单
     * @param distribution
     * @param session
     * @return
     */
    boolean receiving(Distribution distribution, HttpSession session)throws Exception ;

    Distribution sel(UserRelease userRelease);

    /**
     *  拒单
     * @param distribution
     * @return
     */
    int turnDown(Distribution distribution);

    /**
     *  取消
     * @param distribution
     * @return
     */
    int callOff(Distribution distribution);

    /**
     *  申请完成
     * @param distribution
     * @return
     */
    int comple(Distribution distribution);

    /**
     *  增加派单（直接找师傅的情况）
     * @param distribution
     * @return
     */
    int insOrder(Distribution distribution);

    /**
     *  添加订单的评论和 星级
     * @param userOrder
     * @return
     */
    int comment(UserOrder userOrder);

    /**
     *  修改下单信息
     * @param distribution
     * @return
     */
    int updDistribution(Distribution distribution, Integer oldStatus);

}
