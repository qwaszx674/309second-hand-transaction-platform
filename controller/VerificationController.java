package com.daowen.controller;

import com.daowen.entity.Verification;
import com.daowen.service.VerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/verification")
public class VerificationController {

    @Autowired
    private VerificationService verificationService;

    @RequestMapping("/apply")
    public Map<String, Object> apply(Integer listingId) {
        Map<String, Object> result = new HashMap<>();
        try {
            Verification existing = verificationService.getByListingId(listingId);
            if (existing != null) {
                result.put("stateCode", -1);
                result.put("des", "该商品已申请认证");
                return result;
            }
            
            Verification verification = new Verification();
            verification.setListingId(listingId);
            verification.setStatus("pending");
            verification.setSubmitTime(new Date());
            
            verificationService.insert(verification);
            result.put("stateCode", 1);
        } catch (Exception e) {
            result.put("stateCode", -1);
            result.put("des", "申请失败");
        }
        return result;
    }

    @RequestMapping("/audit")
    public Map<String, Object> audit(Integer id, String status, String remark) {
        Map<String, Object> result = new HashMap<>();
        try {
            Verification verification = (Verification) verificationService.load(id);
            if (verification == null) {
                result.put("stateCode", -1);
                result.put("des", "认证记录不存在");
                return result;
            }
            
            verification.setStatus(status);
            verification.setRemark(remark);
            verification.setAuditTime(new Date());
            
            verificationService.update(verification);
            result.put("stateCode", 1);
        } catch (Exception e) {
            result.put("stateCode", -1);
            result.put("des", "审核失败");
        }
        return result;
    }

    @RequestMapping("/status")
    public Map<String, Object> status(Integer listingId) {
        Map<String, Object> result = new HashMap<>();
        try {
            Verification verification = verificationService.getByListingId(listingId);
            result.put("stateCode", 1);
            result.put("data", verification);
        } catch (Exception e) {
            result.put("stateCode", -1);
            result.put("des", "查询失败");
        }
        return result;
    }

    @RequestMapping("/list")
    public Map<String, Object> list(String status) {
        Map<String, Object> result = new HashMap<>();
        try {
            List<?> list;
            if (status != null && !status.isEmpty()) {
                list = verificationService.getByStatus(status);
            } else {
                list = verificationService.query("");
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