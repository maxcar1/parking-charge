package com.maxcar.user.service;

import com.maxcar.user.entity.Customer;

public interface CustomerService {

    /**
     * customer , open_id 查询消费者
     *
     * @param customer
     * @return
     */
    Customer getCustomer(Customer customer);

    void saveCustomer(Customer customer);


}
