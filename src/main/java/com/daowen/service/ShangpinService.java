package com.daowen.service;

import com.daowen.entity.Shangpin;
import com.daowen.mapper.ShangpinMapper;
import com.daowen.ssm.simplecrud.SimpleBizservice;
import org.springframework.stereotype.Service;

@Service
public class ShangpinService extends SimpleBizservice<ShangpinMapper, Shangpin> {

}