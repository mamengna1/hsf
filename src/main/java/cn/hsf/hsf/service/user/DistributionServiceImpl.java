package cn.hsf.hsf.service.user;

import cn.hsf.hsf.mapper.user.DistributionMapper;
import cn.hsf.hsf.mapper.user.UserDetailMapper;
import cn.hsf.hsf.mapper.user.UserOrderMapper;
import cn.hsf.hsf.mapper.user.UserReleaseMapper;
import cn.hsf.hsf.pojo.user.*;
import cn.hsf.hsf.service.message.MessageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class DistributionServiceImpl implements DistributionService {

    @Autowired
    private DistributionMapper distributionMapper;
    @Autowired
    private UserReleaseMapper userReleaseMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private UserOrderMapper userOrderMapper;
    @Autowired
    private UserDetailMapper userDetailMapper;

    @Override
    public List<Distribution> selAllOrderBySfId(Integer sfId) {
        return distributionMapper.selAllOrderBySfId(sfId);
    }

    @Override
    public Distribution selOrderById(Integer id) {
        return distributionMapper.selOrderById(id);
    }

    @Override
    public int selReleaseById(Integer releaseId) {
        return distributionMapper.selReleaseById(releaseId);
    }

    /**
     * 接单后修改状态
     *
     * @param distribution
     * @param session
     * @return
     * @throws Exception
     */
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class})
    @Override
    public boolean receiving(Distribution distribution, HttpSession session) throws Exception {
        System.out.println("DIS : " + distribution);
        // 查出用户订单信息
        UserRelease userRelease = userReleaseMapper.selReleaseById(distribution.getReleaseId());
        // 订单还没有被接单
        if (userRelease.getReceiveId() == null) {
            // 根据下单ID  状态ID  师傅ID 去让这个师傅接单      修改的时候  订单状态必须为1：接单中才能修改成功
            int count = userReleaseMapper.updUserRelease(new UserRelease(distribution.getReleaseId(), distribution.getStatusId(), distribution.getSfId()), 1);
            if (count > 0) {
                UserOrder userOrder = new UserOrder(userRelease.getUserId(), distribution.getSfId());

                userOrderMapper.insUserOrder(userOrder);
                distribution.setOrderId(userOrder.getId());
                System.out.println("接单数据 ：" + distribution);
                // 修改派单表信息   修改的时候   派单信息的状态必须为1 ：接单中
                int count2 = distributionMapper.updDistribution(distribution, 1);
                // 修改用户接单量
                userDetailMapper.updUserTotalOrder(new UserDetail(distribution.getSfId(),1, null));
                if (count2 > 0) {

                    // 只要师傅成功接单  就把该订单的 其他派单记录状态修改
                    distributionMapper.updDistribution(new Distribution(distribution.getReleaseId(), 8), 1);

                    new Thread(() -> {
                        UserRelease userR = userReleaseMapper.selReleaseById(distribution.getReleaseId());
                        User user = userService.selById(userR.getUserId());
                        User sf = userService.selUserByOpenId((String) session.getAttribute("openId"));
                        MessageServiceImpl.sendZhaoSf2(user.getOpenId(),
                                "http://java.86blue.cn/_api/goUserOrderDetail?id=" + userR.getId(),
                                sf.getUserDetail().getName() + "师傅接受了您的雇佣，请等待师傅上门服务。",
                                userR.getTitle(),
                                userOrder.getId() + "", "已接单",
                                "师傅信息：" + sf.getUserDetail().getName() + " : " + sf.getPhone() + "\\n如有疑问请直接联系师傅手机或平台客服。");
                    }).start();
                    return true;
                } else {
                    throw new RuntimeException("订单已经被接受");
                }
            } else {
                throw new RuntimeException("订单已经被接受");
            }
        } else {
            throw new RuntimeException("订单已经被接受");
        }

    }

    @Override
    public Distribution sel(UserRelease userRelease) {

        return distributionMapper.sel(userRelease);
    }

    /**
     * 拒单
     * @param distribution
     * @return
     */
    @Override
    public int turnDown(Distribution distribution) {
        userDetailMapper.updUserTotalOrder(new UserDetail(distribution.getSfId(), null, 1));
        return distributionMapper.updDistribution(distribution, 1);
    }

    /**
     * 已取消
     * @param distribution
     * @return
     */
    @Override
    public int callOff(Distribution distribution) {
        System.out.println("取消订单  ======");
        int count = distributionMapper.updDistribution(distribution, 2);
        if (count > 0) {
            System.out.println("取消订单 ===== ： " + distribution.getSfId());
            userDetailMapper.updUserTotalOrder(new UserDetail(distribution.getSfId(),null, 1));
            // 用户下单表  状态改为 接单中
            return userReleaseMapper.updUserRelease(new UserRelease(distribution.getReleaseId(), 1, null),2);
        }
        return 0;
    }

    /**
     * 申请完成
     * @param distribution
     * @return
     */
    @Override
    public int comple(Distribution distribution) {
        int count = distributionMapper.updDistribution(distribution, 2);
        if (count > 0) {
            UserRelease userRelease = userReleaseMapper.selReleaseById(distribution.getReleaseId());
            return userReleaseMapper.updUserRelease(new UserRelease(userRelease.getId(), 7, userRelease.getReceiveId()),2);
        }
        return 0;
    }

    /**
     *  直接找师傅一对一下单    如果是一对一下单  那么订单取消应该是直接显示预约失败  而不是接单中
     * @param distribution
     * @return
     */
    @Override
    public int insOrder(Distribution distribution) {
        return distributionMapper.insOrder(distribution);
    }

    /**
     *  添加订单的评论和 星级  订单完成
     * @param userOrder
     * @return
     */
    @Override
    public int comment(UserOrder userOrder) {
        int count = userOrderMapper.updUserOrder(userOrder);

        return userOrderMapper.updUserOrder(userOrder);
    }

    @Override
    public int updDistribution(Distribution distribution, Integer oldStatus) {
        return distributionMapper.updDistribution(distribution, oldStatus);
    }


}
