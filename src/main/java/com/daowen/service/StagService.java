package com.daowen.service;

import com.daowen.entity.Stag;
import com.daowen.mapper.StagMapper;
import com.daowen.ssm.simplecrud.SimpleBizservice;
import org.springframework.stereotype.Service;

@Service
public class StagService extends SimpleBizservice<StagMapper, Stag> {

}