package com.daowen.controller;

import com.daowen.entity.Review;
import com.daowen.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @RequestMapping("/save")
    public Map<String, Object> save(Review review) {
        Map<String, Object> result = new HashMap<>();
        try {
            review.setCreateTime(new Date());
            reviewService.insert(review);
            result.put("stateCode", 1);
        } catch (Exception e) {
            result.put("stateCode", -1);
            result.put("des", "评价失败");
        }
        return result;
    }

    @RequestMapping("/list")
    public Map<String, Object> list(Integer sellerId, Integer buyerId) {
        Map<String, Object> result = new HashMap<>();
        try {
            List<?> list;
            if (sellerId != null) {
                list = reviewService.getBySellerId(sellerId);
            } else if (buyerId != null) {
                list = reviewService.getByBuyerId(buyerId);
            } else {
                list = reviewService.query("");
            }
            result.put("stateCode", 1);
            result.put("data", list);
        } catch (Exception e) {
            result.put("stateCode", -1);
            result.put("des", "查询失败");
        }
        return result;
    }
}