package com.daowen.controller;

import com.daowen.entity.Lanmu;
import com.daowen.service.LanmuService;
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
@RequestMapping("/admin/lanmu")
public class LanmuController {

    @Autowired
    private LanmuService lanmuService;

    @RequestMapping("/list")
    @ResponseBody
    public JsonResult list(HttpServletRequest request) {
        try {
            String ispaged = request.getParameter("ispaged");
            String parentid = request.getParameter("parentid");
            
            Map map = new HashMap();
            if (parentid != null && !parentid.isEmpty()) {
                map.put("parentid", Integer.parseInt(parentid));
            }
            
            List<Lanmu> list = lanmuService.find(map);
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
            Lanmu lanmu = (Lanmu) lanmuService.load(Integer.parseInt(id));
            if (lanmu == null) {
                return JsonResult.error(-1, "分类不存在");
            }
            return JsonResult.success(1, "success", lanmu);
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
            
            Lanmu lanmu = new Lanmu();
            lanmu.setName(name);
            lanmu.setDescription(description);
            lanmu.setSort(sort != null ? Integer.parseInt(sort) : 0);
            lanmu.setParentid(0);
            
            lanmuService.insert(lanmu);
            return JsonResult.success(1, "添加成功", lanmu);
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
            
            Lanmu lanmu = (Lanmu) lanmuService.load(Integer.parseInt(id));
            if (lanmu == null) {
                return JsonResult.error(-1, "分类不存在");
            }
            
            lanmu.setName(name);
            lanmu.setDescription(description);
            lanmu.setSort(sort != null ? Integer.parseInt(sort) : 0);
            
            lanmuService.update(lanmu);
            return JsonResult.success(1, "更新成功", lanmu);
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
            lanmuService.delete(Integer.parseInt(id));
            return JsonResult.success(1, "删除成功");
        } catch (Exception e) {
            return JsonResult.error(-1, "删除失败");
        }
    }
}