package com.daowen.service;

import com.daowen.entity.Spcomment;
import com.daowen.mapper.SpcommentMapper;
import com.daowen.ssm.simplecrud.SimpleBizservice;
import org.springframework.stereotype.Service;

@Service
public class SpcommentService extends SimpleBizservice<SpcommentMapper, Spcomment> {

}