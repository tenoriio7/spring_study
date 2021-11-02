package com.example.demo.services;

import com.example.demo.domain.TravelRequest;
import com.example.demo.domain.TravelRequestStatus;
import com.example.demo.interfaces.TravelRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TravelService {

    @Autowired
    TravelRequestRepository travelRequestRepository;

    public TravelRequest saveTravelRequest(com.example.demo.domain.TravelRequest travelRequest){
        travelRequest.setStatus(TravelRequestStatus.ACCEPTED);
        travelRequest.setCreationDate(new Date());
        return  travelRequestRepository.save(travelRequest);
    }
}
