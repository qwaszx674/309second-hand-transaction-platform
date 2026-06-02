package com.daowen.service;

import com.daowen.entity.Orderitem;
import com.daowen.mapper.OrderitemMapper;
import com.daowen.ssm.simplecrud.SimpleBizservice;
import org.springframework.stereotype.Service;

@Service
public class OrderitemService extends SimpleBizservice<OrderitemMapper, Orderitem> {

}