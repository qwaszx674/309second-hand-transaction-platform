package com.daowen.mapper;

import com.daowen.entity.Verification;
import com.daowen.ssm.simplecrud.SimpleMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VerificationMapper extends SimpleMapper {
    
    Verification selectByListingId(int listingId);
}