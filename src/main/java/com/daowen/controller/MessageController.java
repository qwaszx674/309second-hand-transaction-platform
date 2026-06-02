package com.daowen.controller;

import com.daowen.entity.Message;
import com.daowen.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @RequestMapping("/send")
    public Map<String, Object> send(Integer senderId, Integer receiverId, Integer listingId, String content) {
        Map<String, Object> result = new HashMap<>();
        try {
            Message message = new Message();
            message.setSenderId(senderId);
            message.setReceiverId(receiverId);
            message.setListingId(listingId);
            message.setContent(content);
            message.setSendTime(new Date());
            message.setIsRead(0);
            
            messageService.insert(message);
            result.put("stateCode", 1);
        } catch (Exception e) {
            result.put("stateCode", -1);
            result.put("des", "发送失败");
        }
        return result;
    }

    @RequestMapping("/list")
    public Map<String, Object> list(Integer senderId, Integer receiverId, Integer listingId) {
        Map<String, Object> result = new HashMap<>();
        try {
            List<?> list;
            if (senderId != null && receiverId != null) {
                list = messageService.getBySenderAndReceiver(senderId, receiverId);
            } else if (receiverId != null) {
                list = messageService.getByReceiverId(receiverId);
            } else if (listingId != null) {
                list = messageService.getByListingId(listingId);
            } else {
                list = messageService.query("");
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