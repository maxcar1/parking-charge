package com.maxcar.market.service.mapperService;

import com.maxcar.market.dao.PropertyContractPayDetailMapper;
import com.maxcar.market.model.request.GetAllPayDetailRequest;
import com.maxcar.market.pojo.PropertyContractPayDetail;
import com.maxcar.market.utils.ToolUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyContractPayDetailMapperService {

    @Autowired
    private PropertyContractPayDetailMapper propertyContractPayDetailMapper;

    /**
     * param:
     * describe: 获取合同记录列表
     * create_date:  lxy   2018/8/20  15:02
     **/
    public List<PropertyContractPayDetail> getAllPayDetail(GetAllPayDetailRequest request) {
        return propertyContractPayDetailMapper.getAll(request);
    }

    /**
     * param:
     * describe: 新增合同缴费记录
     * create_date:  lxy   2018/8/20  15:23
     **/
    public boolean insertSelective(PropertyContractPayDetail record) {
        return ToolUtils.isOperationSuccess(propertyContractPayDetailMapper.insertSelective(record));
    }

    /**
     * param:
     * describe: 更新合同缴费记录
     * create_date:  lxy   2018/8/21  14:53
     **/
    public boolean updateByPrimaryKeySelective(PropertyContractPayDetail record) {
        return ToolUtils.isOperationSuccess(propertyContractPayDetailMapper.updateByPrimaryKeySelective(record));
    }
    
    /**
     * param:
     * describe: 查看合同记录表主键
     * create_date:  lxy   2018/8/21  14:56 
     **/
    public PropertyContractPayDetail selectByPrimaryKey(String propertyContractPayDetailId) {
        return propertyContractPayDetailMapper.selectByPrimaryKey(propertyContractPayDetailId);
    }


}
