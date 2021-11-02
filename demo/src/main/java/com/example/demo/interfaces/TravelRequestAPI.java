package com.example.demo.interfaces;

import com.example.demo.DTO.TravelRequestInput;
import com.example.demo.domain.TravelRequest;
import com.example.demo.interfaces.output.TravelRequestOutput;
import com.example.demo.mapper.TravelRequestMapper;
import com.example.demo.services.TravelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.transform.TransformerException;

@Service
@RestController
@RequestMapping(path = "/travelRequest", produces = MediaType.APPLICATION_JSON_VALUE)
public class TravelRequestAPI {

    @Autowired
    TravelService travelService;

    @Autowired
    TravelRequestMapper mapper;

    @PostMapping
    public EntityModel<TravelRequestOutput> makeTravelRequest(@RequestBody TravelRequestInput travelRequestInput){
        TravelRequest request = travelService.saveTravelRequest(mapper.map(travelRequestInput));
        TravelRequestOutput output = mapper.map((request));
        return mapper.buildOutPutModel(request,output);
    }
}
