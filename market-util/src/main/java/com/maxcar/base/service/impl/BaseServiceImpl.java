package com.maxcar.base.service.impl;



import com.maxcar.base.dao.BaseDao;
import com.maxcar.base.service.BaseService;

import java.io.Serializable;

/**
 * 业务逻辑层，通用实现类
 *
 * @auther  罗顺锋
 * @create 2018/8/13
 */
public abstract class BaseServiceImpl<T, ID extends Serializable> implements BaseService<T, ID> {
//    public BaseServiceImpl(){
//        System.out.print(11);
//    }
    public abstract BaseDao<T, ID> getBaseMapper();

    @Override
    public int insertSelective(T record) {
        return getBaseMapper().insertSelective(record);
    }

    @Override
    public T selectByPrimaryKey(ID id) {
        return getBaseMapper().selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(T record) {
        return getBaseMapper().updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(T record) {
        return getBaseMapper().updateByPrimaryKey(record);
    }

    @Override
    public int deleteByPrimaryKey(ID id) {
        return getBaseMapper().deleteByPrimaryKey(id);
    }

    @Override
    public int insert(T record) {
        return getBaseMapper().insert(record);
    }
}
