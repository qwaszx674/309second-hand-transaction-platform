package com.daowen.service;

import com.daowen.entity.Review;
import com.daowen.mapper.ReviewMapper;
import com.daowen.ssm.simplecrud.SimpleBizservice;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService extends SimpleBizservice<ReviewMapper, Review> {

    public List<Review> getBySellerId(int sellerId) {
        return mapper.selectBySellerId(sellerId);
    }

    public List<Review> getByBuyerId(int buyerId) {
        return mapper.selectByBuyerId(buyerId);
    }
}