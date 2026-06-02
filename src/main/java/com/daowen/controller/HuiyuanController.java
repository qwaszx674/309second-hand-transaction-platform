package com.daowen.controller;

import com.daowen.entity.Huiyuan;
import com.daowen.service.HuiyuanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/huiyuan")
public class HuiyuanController {

    @Autowired
    private HuiyuanService huiyuanService;

    @RequestMapping("/register")
    public Map<String, Object> register(@RequestParam String username, @RequestParam String password, String mobile, String email) {
        Map<String, Object> result = new HashMap<>();
        
        if (username == null || username.trim().isEmpty()) {
            result.put("stateCode", -1);
            result.put("des", "请输入用户名");
            return result;
        }
        
        if (password == null || password.trim().isEmpty()) {
            result.put("stateCode", -1);
            result.put("des", "请输入密码");
            return result;
        }

        Huiyuan existing = huiyuanService.getEntity("username='" + username + "'", true);
        if (existing != null) {
            result.put("stateCode", -1);
            result.put("des", "用户名已存在");
            return result;
        }

        if (mobile != null && !mobile.isEmpty()) {
            existing = huiyuanService.getEntity("mobile='" + mobile + "'", true);
            if (existing != null) {
                result.put("stateCode", -1);
                result.put("des", "手机号已被注册");
                return result;
            }
        }

        try {
            Huiyuan huiyuan = new Huiyuan();
            huiyuan.setUsername(username);
            huiyuan.setPassword(password);
            huiyuan.setMobile(mobile);
            huiyuan.setEmail(email);
            huiyuan.setCreatetime(new Date());
            huiyuan.setState(1);
            huiyuan.setRole("buyer");
            
            huiyuanService.insert(huiyuan);
            result.put("stateCode", 1);
            result.put("data", huiyuan);
        } catch (Exception e) {
            result.put("stateCode", -1);
            result.put("des", "注册失败");
        }
        
        return result;
    }

    @RequestMapping("/login")
    public Map<String, Object> login(HttpServletRequest request, @RequestParam String username, @RequestParam String password) {
        Map<String, Object> result = new HashMap<>();
        
        if (username == null || username.trim().isEmpty()) {
            result.put("stateCode", -1);
            result.put("des", "请输入用户名");
            return result;
        }
        
        if (password == null || password.trim().isEmpty()) {
            result.put("stateCode", -1);
            result.put("des", "请输入密码");
            return result;
        }

        Huiyuan huiyuan = huiyuanService.getEntity("username='" + username + "' and password='" + password + "'", true);
        
        if (huiyuan != null) {
            if (huiyuan.getState() == 0) {
                result.put("stateCode", -1);
                result.put("des", "账号已被封禁");
                return result;
            }
            HttpSession session = request.getSession();
            session.setAttribute("huiyuan", huiyuan);
            result.put("stateCode", 1);
            result.put("data", huiyuan);
        } else {
            result.put("stateCode", -1);
            result.put("des", "用户名或密码错误");
        }
        
        return result;
    }

    @RequestMapping("/load")
    public Map<String, Object> load(String id) {
        Map<String, Object> result = new HashMap<>();
        
        if (id == null || id.trim().isEmpty()) {
            result.put("stateCode", -1);
            result.put("des", "参数错误");
            return result;
        }

        try {
            int hid = Integer.parseInt(id);
            Huiyuan huiyuan = (Huiyuan) huiyuanService.load(hid);
            
            if (huiyuan != null) {
                result.put("stateCode", 1);
                result.put("data", huiyuan);
            } else {
                result.put("stateCode", -1);
                result.put("des", "用户不存在");
            }
        } catch (NumberFormatException e) {
            result.put("stateCode", -1);
            result.put("des", "参数错误");
        }
        
        return result;
    }

    @RequestMapping("/save")
    public Map<String, Object> save(Huiyuan huiyuan) {
        Map<String, Object> result = new HashMap<>();
        
        try {
            if (huiyuan.getId() > 0) {
                huiyuanService.update(huiyuan);
            } else {
                huiyuan.setCreatetime(new Date());
                huiyuan.setState(1);
                huiyuanService.insert(huiyuan);
            }
            result.put("stateCode", 1);
        } catch (Exception e) {
            result.put("stateCode", -1);
            result.put("des", "保存失败");
        }
        
        return result;
    }

    @RequestMapping("/update")
    public Map<String, Object> update(Huiyuan huiyuan) {
        Map<String, Object> result = new HashMap<>();
        
        try {
            huiyuanService.update(huiyuan);
            result.put("stateCode", 1);
        } catch (Exception e) {
            result.put("stateCode", -1);
            result.put("des", "更新失败");
        }
        
        return result;
    }

    @RequestMapping("/list")
    public Map<String, Object> list(String ispaged) {
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

    @RequestMapping("/logout")
    public Map<String, Object> logout(HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();
        
        try {
            HttpSession session = request.getSession();
            session.removeAttribute("huiyuan");
            session.invalidate();
            result.put("stateCode", 1);
        } catch (Exception e) {
            result.put("stateCode", -1);
            result.put("des", "退出失败");
        }
        
        return result;
    }
}