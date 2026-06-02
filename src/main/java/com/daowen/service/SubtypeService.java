package com.daowen.service;

import com.daowen.entity.Subtype;
import com.daowen.mapper.SubtypeMapper;
import com.daowen.ssm.simplecrud.SimpleBizservice;
import org.springframework.stereotype.Service;

@Service
public class SubtypeService extends SimpleBizservice<SubtypeMapper, Subtype> {

}