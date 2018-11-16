package com.maxcar.stock.service.impl;

import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.util.UuidUtils;
import com.maxcar.stock.dao.CarChannelMapper;
import com.maxcar.stock.dao.CarChannelRelMapper;
import com.maxcar.stock.pojo.CarChannel;
import com.maxcar.stock.pojo.CarChannelExample;
import com.maxcar.stock.pojo.CarChannelRel;
import com.maxcar.stock.pojo.CarChannelRelExample;
import com.maxcar.stock.service.CarChannelService;
import com.maxcar.stock.vo.CarChannelVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author songxuefeng
 * @create 2018-08-21 15:48
 * @description: ${description}
 **/
@Service("carChannelService")
public class CarChannelServiceImpl implements CarChannelService {
    @Autowired
    private CarChannelMapper carChannelMapper;

    @Autowired
    private CarChannelRelMapper carChannelRelMapper;

    @Override
    public List<CarChannelVo> getCarchannel(String carId) {
        return carChannelMapper.getCarchannel(carId);
    }

    @Override
    public List<CarChannelVo> getCarchannelsByCar(String carId) {
        return carChannelMapper.getCarchannelsByCar(carId);
    }

    @Override
    public void upCar(String carId, String channelId) {
        CarChannelRelExample carChannelRelExample=new CarChannelRelExample();
        CarChannelRelExample.Criteria criteria=carChannelRelExample.createCriteria();
        criteria.andCarIdEqualTo(carId).andChannelIdEqualTo(channelId);
        List<CarChannelRel> carChannelRels=carChannelRelMapper.selectByExample(carChannelRelExample);

        CarChannelRel carChannelRel;
        if (carChannelRels!=null&&carChannelRels.size()>0){
            carChannelRel=carChannelRels.get(0);
            carChannelRel.setShelfStatus(1);//上架
            carChannelRel.setUpdateTime(new Date());
            carChannelRelMapper.updateByPrimaryKeySelective(carChannelRel);
        }else {
            carChannelRel =new CarChannelRel();
            carChannelRel.setId(UuidUtils.generateIdentifier());
            carChannelRel.setCarId(carId);
            carChannelRel.setChannelId(channelId);
            carChannelRel.setShelfStatus(1);//上架
            carChannelRel.setUpdateTime(new Date());
            carChannelRelMapper.insertSelective(carChannelRel);
        }

    }

    @Override
    public InterfaceResult downCar(String carId, String channelId) {
        InterfaceResult result = new InterfaceResult();
        CarChannelRelExample carChannelRelExample=new CarChannelRelExample();
        CarChannelRelExample.Criteria criteria=carChannelRelExample.createCriteria();
        criteria.andCarIdEqualTo(carId).andChannelIdEqualTo(channelId);
        List<CarChannelRel> carChannelRels=carChannelRelMapper.selectByExample(carChannelRelExample);
        if (carChannelRels!=null&&carChannelRels.size()>0){
            CarChannelRel carChannelRel=carChannelRels.get(0);
            carChannelRel.setShelfStatus(0);//下架
            carChannelRel.setUpdateTime(new Date());
            carChannelRelMapper.updateByPrimaryKeySelective(carChannelRel);
        }else {
            result.InterfaceResult600("查找不到数据");
        }

        return result;
    }

    @Override
    public List<CarChannelVo> getCarShelfNum(String marketId) {
        return carChannelMapper.getCarShelfNum(marketId);
    }

    @Override
    public List<CarChannel> findAll(String marketId) {
        CarChannelExample carChannelExample=new CarChannelExample();
        CarChannelExample.Criteria criteria=carChannelExample.createCriteria();
        criteria.andIsvalidEqualTo(1).andMarketIdEqualTo(marketId);
        return carChannelMapper.selectByExample(carChannelExample);
    }

    @Override
    public CarChannelRel getCarchannelRel(String carId, String id) {
        CarChannelRelExample carChannelRelExample=new CarChannelRelExample();
        CarChannelRelExample.Criteria criteria=carChannelRelExample.createCriteria();
        criteria.andCarIdEqualTo(carId)
                .andChannelIdEqualTo(id)
                .andIsvalidEqualTo(1);
        List<CarChannelRel> carChannelRels=carChannelRelMapper.selectByExample(carChannelRelExample);
        if (carChannelRels!=null&&carChannelRels.size()>0){
            return carChannelRels.get(0);
        }
        return null;
    }

    @Override
    public void updateChanelByCarId(CarChannelRel carChannelRel) {
        carChannelRelMapper.updateByPrimaryKeySelective(carChannelRel);
    }

    @Override
    public void insertChannel(CarChannelRel carChannelRel) {
        carChannelRel.setId(UuidUtils.generateIdentifier());


        carChannelRel.setUpdateTime(new Date());

        carChannelRelMapper.insert(carChannelRel);
    }

}
