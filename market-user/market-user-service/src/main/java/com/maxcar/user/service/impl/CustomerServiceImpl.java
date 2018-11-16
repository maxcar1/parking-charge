package com.maxcar.user.service.impl;

import com.maxcar.user.dao.CustomerMapper;
import com.maxcar.user.entity.Customer;
import com.maxcar.user.service.CustomerService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service("customerService")
public class CustomerServiceImpl implements CustomerService {

    @Resource
    private CustomerMapper customerMapper;

    @Override
    public Customer getCustomer(Customer customer) {
        Customer customerInfo = new Customer();
        try {

            customerInfo = customerMapper.selectCustomer(customer);
        } catch (Exception e) {
            Logger.getRootLogger().error(e, e);
        }
        return customerInfo;
    }

    @Override
    public void saveCustomer(Customer customer) {
        try {
            customerMapper.insertSelective(customer);
        } catch (Exception e) {
            Logger.getRootLogger().error(e, e);
        }
    }


}
