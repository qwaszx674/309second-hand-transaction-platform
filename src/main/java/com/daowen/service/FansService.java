package com.daowen.service;

import com.daowen.entity.Fans;
import com.daowen.mapper.FansMapper;
import com.daowen.ssm.simplecrud.SimpleBizservice;
import org.springframework.stereotype.Service;

@Service
public class FansService extends SimpleBizservice<FansMapper, Fans> {

}