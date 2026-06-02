package com.daowen.service;

import com.daowen.entity.Message;
import com.daowen.mapper.MessageMapper;
import com.daowen.ssm.simplecrud.SimpleBizservice;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService extends SimpleBizservice<MessageMapper, Message> {

    public List<Message> getBySenderAndReceiver(int senderId, int receiverId) {
        return mapper.selectBySenderAndReceiver(senderId, receiverId);
    }

    public List<Message> getByReceiverId(int receiverId) {
        return mapper.selectByReceiverId(receiverId);
    }

    public List<Message> getByListingId(int listingId) {
        return mapper.selectByListingId(listingId);
    }
}