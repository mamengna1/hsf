package cn.hsf.hsf.mapper.user;

import cn.hsf.hsf.pojo.user.UserOrder;

public interface UserOrderMapper {

    /**
     *  根据订单号查询  订单详情
     * @param id
     * @return
     */
    UserOrder selById(Integer id);

    /**
     *  师傅接单  默认生成一条数据 给 下单表
     * @param userOrder
     * @return
     */
    int insUserOrder(UserOrder userOrder);

    /**
     *  修改订单    增加评论和评分
     * @param userOrder
     * @return
     */
    int updUserOrder(UserOrder userOrder);

}
