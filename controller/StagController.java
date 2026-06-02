package com.daowen.controller;

import com.daowen.entity.Stag;
import com.daowen.service.StagService;
import com.daowen.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/stag")
public class StagController {

    @Autowired
    private StagService stagService;

    @RequestMapping("/list")
    @ResponseBody
    public JsonResult list(HttpServletRequest request) {
        try {
            String ispaged = request.getParameter("ispaged");
            
            List<Stag> list = stagService.selectAll();
            return JsonResult.success(1, "success", list);
        } catch (Exception e) {
            return JsonResult.error(-1, "加载失败");
        }
    }

    @RequestMapping("/info")
    @ResponseBody
    public JsonResult info(HttpServletRequest request) {
        try {
            String id = request.getParameter("id");
            if (id == null || id.isEmpty()) {
                return JsonResult.error(-1, "参数错误");
            }
            Stag stag = (Stag) stagService.load(Integer.parseInt(id));
            if (stag == null) {
                return JsonResult.error(-1, "标签不存在");
            }
            return JsonResult.success(1, "success", stag);
        } catch (Exception e) {
            return JsonResult.error(-1, "加载失败");
        }
    }

    @RequestMapping("/save")
    @ResponseBody
    public JsonResult save(HttpServletRequest request) {
        try {
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            String sort = request.getParameter("sort");
            
            Stag stag = new Stag();
            stag.setName(name);
            stag.setDescription(description);
            stag.setSort(sort != null ? Integer.parseInt(sort) : 0);
            
            stagService.insert(stag);
            return JsonResult.success(1, "添加成功", stag);
        } catch (Exception e) {
            return JsonResult.error(-1, "添加失败");
        }
    }

    @RequestMapping("/update")
    @ResponseBody
    public JsonResult update(HttpServletRequest request) {
        try {
            String id = request.getParameter("id");
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            String sort = request.getParameter("sort");
            
            Stag stag = (Stag) stagService.load(Integer.parseInt(id));
            if (stag == null) {
                return JsonResult.error(-1, "标签不存在");
            }
            
            stag.setName(name);
            stag.setDescription(description);
            stag.setSort(sort != null ? Integer.parseInt(sort) : 0);
            
            stagService.update(stag);
            return JsonResult.success(1, "更新成功", stag);
        } catch (Exception e) {
            return JsonResult.error(-1, "更新失败");
        }
    }

    @RequestMapping("/delete")
    @ResponseBody
    public JsonResult delete(HttpServletRequest request) {
        try {
            String id = request.getParameter("id");
            if (id == null || id.isEmpty()) {
                return JsonResult.error(-1, "参数错误");
            }
            stagService.delete(Integer.parseInt(id));
            return JsonResult.success(1, "删除成功");
        } catch (Exception e) {
            return JsonResult.error(-1, "删除失败");
        }
    }
}