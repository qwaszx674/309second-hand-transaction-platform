package com.daowen.service;

import com.daowen.entity.Shorder;
import com.daowen.mapper.ShorderMapper;
import com.daowen.ssm.simplecrud.SimpleBizservice;
import com.daowen.util.JsonResult;
import com.daowen.vo.CreateOrderDTO;
import com.daowen.vo.OrderDTO;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class ShorderService extends SimpleBizservice<ShorderMapper, Shorder> {

    public List<HashMap<String, Object>> saleStat(HashMap<String, Object> map) {
        return mapper.saleStat(map);
    }

    public int deduct(int purchaser, double totalfee) {
        return 1;
    }

    public Boolean changeToPayed(int id) {
        return true;
    }

    public JsonResult validateStock(CreateOrderDTO orderDTO) {
        return JsonResult.success(1, "验证通过");
    }

    public void createOrder(CreateOrderDTO orderDTO) {
    }

    @SuppressWarnings("unchecked")
    public List<OrderDTO> getOrderDTOList(HashMap<String, Object> map) {
        return (List<OrderDTO>) mapper.getEntityPlus(map);
    }

    @SuppressWarnings("unchecked")
    public OrderDTO getOrderDTO(HashMap<String, Object> map) {
        return (OrderDTO) mapper.loadPlus(map);
    }
}