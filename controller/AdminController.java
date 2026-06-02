package com.daowen.controller;

import com.daowen.entity.Huiyuan;
import com.daowen.entity.Shangpin;
import com.daowen.service.HuiyuanService;
import com.daowen.service.ShangpinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/admin")
public class AdminController {

    @Autowired
    private ShangpinService shangpinService;

    @Autowired
    private HuiyuanService huiyuanService;

    @RequestMapping("/shangpin/list")
    public Map<String, Object> listShangpin() {
        Map<String, Object> result = new HashMap<>();
        try {
            List<?> list = shangpinService.query("");
            result.put("stateCode", 1);
            result.put("data", list);
        } catch (Exception e) {
            result.put("stateCode", -1);
            result.put("des", "查询失败");
        }
        return result;
    }

    @RequestMapping("/shangpin/delete")
    public Map<String, Object> deleteShangpin(String id) {
        Map<String, Object> result = new HashMap<>();
        try {
            shangpinService.delete(Integer.parseInt(id));
            result.put("stateCode", 1);
        } catch (Exception e) {
            result.put("stateCode", -1);
            result.put("des", "删除失败");
        }
        return result;
    }

    @RequestMapping("/huiyuan/list")
    public Map<String, Object> listHuiyuan() {
        Map<String, Object> result = new HashMap<>();
        try {
            List<?> list = huiyuanService.query("");
            result.put("stateCode", 1);
            result.put("data", list);
        } catch (Exception e) {
            result.put("stateCode", -1);
            result.put("des", "查询失败");
        }
        return result;
    }

    @RequestMapping("/huiyuan/ban")
    public Map<String, Object> banHuiyuan(String id) {
        Map<String, Object> result = new HashMap<>();
        try {
            Huiyuan huiyuan = (Huiyuan) huiyuanService.load(Integer.parseInt(id));
            if (huiyuan != null) {
                huiyuan.setState(0);
                huiyuanService.update(huiyuan);
                result.put("stateCode", 1);
            } else {
                result.put("stateCode", -1);
                result.put("des", "用户不存在");
            }
        } catch (Exception e) {
            result.put("stateCode", -1);
            result.put("des", "操作失败");
        }
        return result;
    }

    @RequestMapping("/huiyuan/unban")
    public Map<String, Object> unbanHuiyuan(String id) {
        Map<String, Object> result = new HashMap<>();
        try {
            Huiyuan huiyuan = (Huiyuan) huiyuanService.load(Integer.parseInt(id));
            if (huiyuan != null) {
                huiyuan.setState(1);
                huiyuanService.update(huiyuan);
                result.put("stateCode", 1);
            } else {
                result.put("stateCode", -1);
                result.put("des", "用户不存在");
            }
        } catch (Exception e) {
            result.put("stateCode", -1);
            result.put("des", "操作失败");
        }
        return result;
    }
}