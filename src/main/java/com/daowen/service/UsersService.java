package com.daowen.service;

import com.daowen.entity.Users;
import com.daowen.mapper.UsersMapper;
import com.daowen.ssm.simplecrud.SimpleBizservice;
import org.springframework.stereotype.Service;

@Service
public class UsersService extends SimpleBizservice<UsersMapper, Users> {

}