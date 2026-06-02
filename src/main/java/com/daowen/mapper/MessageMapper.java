package com.daowen.mapper;

import com.daowen.entity.Message;
import com.daowen.ssm.simplecrud.SimpleMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MessageMapper extends SimpleMapper {
    
    List<Message> selectBySenderAndReceiver(@Param("senderId") int senderId, @Param("receiverId") int receiverId);
    
    List<Message> selectByReceiverId(int receiverId);
    
    List<Message> selectByListingId(int listingId);
}