package com.daowen.service;

import com.daowen.entity.Huiyuan;
import com.daowen.mapper.HuiyuanMapper;
import com.daowen.ssm.simplecrud.SimpleBizservice;
import org.springframework.stereotype.Service;

@Service
public class HuiyuanService extends SimpleBizservice<HuiyuanMapper, Huiyuan> {

    public int deduct(int hyid, double amount) {
        return mapper.deduct(hyid, amount);
    }
}