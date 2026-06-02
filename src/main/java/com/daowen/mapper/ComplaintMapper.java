package com.daowen.mapper;

import com.daowen.entity.Complaint;
import com.daowen.ssm.simplecrud.SimpleMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ComplaintMapper extends SimpleMapper {
    
    List<Complaint> selectByUserId(int userId);
    
    List<Complaint> selectByStatus(String status);
}