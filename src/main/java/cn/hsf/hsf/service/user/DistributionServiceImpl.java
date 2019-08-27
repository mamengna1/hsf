package cn.hsf.hsf.service.user;

import cn.hsf.hsf.mapper.user.DistributionMapper;
import cn.hsf.hsf.pojo.user.Distribution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistributionServiceImpl implements DistributionService {

    @Autowired
    private DistributionMapper distributionMapper;


    @Override
    public List<Distribution> selAllOrderBySfId(Integer sfId) {
        return distributionMapper.selAllOrderBySfId(sfId);
    }

    @Override
    public Distribution selOrderById(Integer id) {
        return distributionMapper.selOrderById(id);
    }
}
