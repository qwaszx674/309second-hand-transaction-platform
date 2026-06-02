package com.daowen.controller;

import com.daowen.entity.Shoucang;
import com.daowen.service.ShoucangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/shoucang")
public class ShoucangController {

    @Autowired
    private ShoucangService shoucangService;

    @RequestMapping("/save")
    public Map<String, Object> save(Shoucang shoucang) {
        Map<String, Object> result = new HashMap<>();
        try {
            shoucangService.insert(shoucang);
            result.put("stateCode", 1);
        } catch (Exception e) {
            result.put("stateCode", -1);
            result.put("des", "收藏失败");
        }
        return result;
    }

    @RequestMapping("/delete")
    public Map<String, Object> delete(String id) {
        Map<String, Object> result = new HashMap<>();
        try {
            shoucangService.delete(Integer.parseInt(id));
            result.put("stateCode", 1);
        } catch (Exception e) {
            result.put("stateCode", -1);
            result.put("des", "取消收藏失败");
        }
        return result;
    }

    @RequestMapping("/list")
    public Map<String, Object> list(Integer hyid) {
        Map<String, Object> result = new HashMap<>();
        try {
            List<?> list = shoucangService.query("hyid=" + hyid + " and xtype='shangpin'");
            result.put("stateCode", 1);
            result.put("data", list);
        } catch (Exception e) {
            result.put("stateCode", -1);
            result.put("des", "查询失败");
        }
        return result;
    }

    @RequestMapping("/check")
    public Map<String, Object> check(Integer hyid, Integer targetid) {
        Map<String, Object> result = new HashMap<>();
        try {
            Shoucang sc = (Shoucang) shoucangService.getEntity("hyid=" + hyid + " and targetid=" + targetid + " and xtype='shangpin'");
            result.put("stateCode", 1);
            result.put("data", sc != null);
        } catch (Exception e) {
            result.put("stateCode", -1);
            result.put("des", "查询失败");
        }
        return result;
    }
}