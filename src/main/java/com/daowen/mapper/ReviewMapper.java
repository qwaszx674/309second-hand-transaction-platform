package com.daowen.mapper;

import com.daowen.entity.Review;
import com.daowen.ssm.simplecrud.SimpleMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReviewMapper extends SimpleMapper {
    
    List<Review> selectBySellerId(int sellerId);
    
    List<Review> selectByBuyerId(int buyerId);
}