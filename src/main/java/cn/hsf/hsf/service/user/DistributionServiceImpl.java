package cn.hsf.hsf.service.user;

import cn.hsf.hsf.mapper.user.DistributionMapper;
import cn.hsf.hsf.mapper.user.UserReleaseMapper;
import cn.hsf.hsf.pojo.user.Distribution;
import cn.hsf.hsf.pojo.user.UserRelease;
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
     *  接单后修改状态
     * @param distribution
     * @param session
     * @return
     * @throws Exception
     */
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class})
    @Override
    public boolean receiving(Distribution distribution, HttpSession session) throws Exception {
        System.out.println("DIS : " + distribution);
        UserRelease userRelease = userReleaseMapper.selReleaseById(distribution.getReleaseId());
        if (userRelease.getReceiveId() == null) {
            int count = userReleaseMapper.updUserRelease(new UserRelease(distribution.getReleaseId(), distribution.getStatusId(), distribution.getSfId()));
            if (count > 0) {
                int count2 = distributionMapper.updDistribution(distribution);
                if (count2 > 0) {
                    distributionMapper.updDistribution(new Distribution(3));
                    return true;
                }else {
                    throw new RuntimeException("订单已经被接受");
                }
            }else {
                throw new RuntimeException("订单已经被接受");
            }
        } else {
            throw new RuntimeException("订单已经被接受");
        }

    }
}
