package com.daowen.controller;

import com.daowen.entity.Shangpin;
import com.daowen.service.ShangpinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/shangpin")
public class ShangpinController {

    @Autowired
    private ShangpinService shangpinService;

    @RequestMapping("/save")
    public Map<String, Object> save(Shangpin shangpin) {
        Map<String, Object> result = new HashMap<>();
        try {
            if (shangpin.getId() > 0) {
                shangpinService.update(shangpin);
            } else {
                shangpinService.insert(shangpin);
            }
            result.put("stateCode", 1);
        } catch (Exception e) {
            result.put("stateCode", -1);
            result.put("des", "保存失败");
        }
        return result;
    }

    @RequestMapping("/update")
    public Map<String, Object> update(Shangpin shangpin) {
        Map<String, Object> result = new HashMap<>();
        try {
            shangpinService.update(shangpin);
            result.put("stateCode", 1);
        } catch (Exception e) {
            result.put("stateCode", -1);
            result.put("des", "更新失败");
        }
        return result;
    }

    @RequestMapping("/delete")
    public Map<String, Object> delete(String id) {
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

    @RequestMapping("/load")
    public Map<String, Object> load(String id) {
        Map<String, Object> result = new HashMap<>();
        try {
            Shangpin shangpin = (Shangpin) shangpinService.load(Integer.parseInt(id));
            if (shangpin != null) {
                result.put("stateCode", 1);
                result.put("data", shangpin);
            } else {
                result.put("stateCode", -1);
                result.put("des", "商品不存在");
            }
        } catch (Exception e) {
            result.put("stateCode", -1);
            result.put("des", "加载失败");
        }
        return result;
    }

    @RequestMapping("/list")
    public Map<String, Object> list(String ispaged) {
        Map<String, Object> result = new HashMap<>();
        try {
            List<?> list = shangpinService.query("state=1");
            result.put("stateCode", 1);
            result.put("data", list);
        } catch (Exception e) {
            result.put("stateCode", -1);
            result.put("des", "查询失败");
        }
        return result;
    }

    @RequestMapping("/pagelist")
    public Map<String, Object> pagelist(Integer currentpageindex, Integer pagesize, Integer typeid, Integer subtypeid) {
        Map<String, Object> result = new HashMap<>();
        try {
            String where = "state=1";
            if (typeid != null && typeid > 0) {
                where += " and typeid=" + typeid;
            }
            if (subtypeid != null && subtypeid > 0) {
                where += " and subtypeid=" + subtypeid;
            }
            
            List<?> list = shangpinService.query(where);
            Map<String, Object> pageInfo = new HashMap<>();
            pageInfo.put("list", list);
            pageInfo.put("total", list.size());
            
            result.put("stateCode", 1);
            result.put("data", pageInfo);
        } catch (Exception e) {
            result.put("stateCode", -1);
            result.put("des", "查询失败");
        }
        return result;
    }

    @RequestMapping("/hotsales")
    public Map<String, Object> hotsales() {
        Map<String, Object> result = new HashMap<>();
        try {
            List<?> list = shangpinService.query("state=1 order by clickcount desc limit 10");
            result.put("stateCode", 1);
            result.put("data", list);
        } catch (Exception e) {
            result.put("stateCode", -1);
            result.put("des", "查询失败");
        }
        return result;
    }

    @RequestMapping("/search")
    public Map<String, Object> search(String keyword, String brand, String category) {
        Map<String, Object> result = new HashMap<>();
        try {
            String where = "state=1";
            if (keyword != null && !keyword.isEmpty()) {
                where += " and name like '%" + keyword + "%'";
            }
            if (brand != null && !brand.isEmpty()) {
                where += " and chandi='" + brand + "'";
            }
            if (category != null && !category.isEmpty()) {
                where += " and typeid in (select id from lanmu where name like '%" + category + "%')";
            }
            
            List<?> list = shangpinService.query(where);
            result.put("stateCode", 1);
            result.put("data", list);
        } catch (Exception e) {
            result.put("stateCode", -1);
            result.put("des", "搜索失败");
        }
        return result;
    }

    @RequestMapping("/filter")
    public Map<String, Object> filter(Integer minPrice, Integer maxPrice, String location, String condition) {
        Map<String, Object> result = new HashMap<>();
        try {
            String where = "state=1";
            if (minPrice != null) {
                where += " and hyjia>=" + minPrice;
            }
            if (maxPrice != null) {
                where += " and hyjia<=" + maxPrice;
            }
            if (location != null && !location.isEmpty()) {
                where += " and chandi like '%" + location + "%'";
            }
            
            List<?> list = shangpinService.query(where);
            result.put("stateCode", 1);
            result.put("data", list);
        } catch (Exception e) {
            result.put("stateCode", -1);
            result.put("des", "筛选失败");
        }
        return result;
    }
}