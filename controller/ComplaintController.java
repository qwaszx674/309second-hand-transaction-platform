package com.daowen.controller;

import com.daowen.entity.Complaint;
import com.daowen.service.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/complaint")
public class ComplaintController {

    @Autowired
    private ComplaintService complaintService;

    @RequestMapping("/save")
    public Map<String, Object> save(Complaint complaint) {
        Map<String, Object> result = new HashMap<>();
        try {
            complaint.setStatus("pending");
            complaint.setCreateTime(new Date());
            complaintService.insert(complaint);
            result.put("stateCode", 1);
        } catch (Exception e) {
            result.put("stateCode", -1);
            result.put("des", "投诉失败");
        }
        return result;
    }

    @RequestMapping("/handle")
    public Map<String, Object> handle(Integer id, String status, String remark) {
        Map<String, Object> result = new HashMap<>();
        try {
            Complaint complaint = (Complaint) complaintService.load(id);
            if (complaint == null) {
                result.put("stateCode", -1);
                result.put("des", "投诉记录不存在");
                return result;
            }
            
            complaint.setStatus(status);
            complaint.setRemark(remark);
            complaint.setHandleTime(new Date());
            
            complaintService.update(complaint);
            result.put("stateCode", 1);
        } catch (Exception e) {
            result.put("stateCode", -1);
            result.put("des", "处理失败");
        }
        return result;
    }

    @RequestMapping("/list")
    public Map<String, Object> list(Integer userId, String status) {
        Map<String, Object> result = new HashMap<>();
        try {
            List<?> list;
            if (userId != null) {
                list = complaintService.getByUserId(userId);
            } else if (status != null && !status.isEmpty()) {
                list = complaintService.getByStatus(status);
            } else {
                list = complaintService.query("");
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