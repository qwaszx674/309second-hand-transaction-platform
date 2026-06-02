package com.daowen.service;

import com.daowen.entity.Verification;
import com.daowen.mapper.VerificationMapper;
import com.daowen.ssm.simplecrud.SimpleBizservice;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VerificationService extends SimpleBizservice<VerificationMapper, Verification> {

    public Verification getByListingId(int listingId) {
        return mapper.selectByListingId(listingId);
    }

    public List<Verification> getByStatus(String status) {
        return query("status='" + status + "'");
    }
}