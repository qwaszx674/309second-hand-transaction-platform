package com.daowen.service;

import com.daowen.entity.Complaint;
import com.daowen.mapper.ComplaintMapper;
import com.daowen.ssm.simplecrud.SimpleBizservice;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComplaintService extends SimpleBizservice<ComplaintMapper, Complaint> {

    public List<Complaint> getByUserId(int userId) {
        return mapper.selectByUserId(userId);
    }

    public List<Complaint> getByStatus(String status) {
        return mapper.selectByStatus(status);
    }
}