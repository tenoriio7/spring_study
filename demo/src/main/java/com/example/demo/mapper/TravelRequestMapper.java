package com.example.demo.mapper;

import com.example.demo.DTO.TravelRequestInput;
import com.example.demo.domain.Passenger;
import com.example.demo.domain.PassengerRespository;
import com.example.demo.domain.TravelRequest;
import com.example.demo.interfaces.PassengerAPI;
import com.example.demo.interfaces.output.TravelRequestOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import javax.swing.text.html.parser.Entity;

@Component
public class TravelRequestMapper {

    @Autowired

    private PassengerRespository passengerRespository;


    public TravelRequest map (TravelRequestInput input){
        Passenger passenger = passengerRespository.findById(input.getPassengerId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        TravelRequest travelRequest =  new TravelRequest();
        travelRequest.setOrigin(input.getOrigin());
        travelRequest.setDestination(input.getDestination());
        travelRequest.setPassenger(passenger);

        return travelRequest;
    }

    public TravelRequestOutput map(TravelRequest travelRequest){
        TravelRequestOutput travelRequestOutput = new TravelRequestOutput();
        travelRequestOutput.setCreationDate(travelRequest.getCreationDate());
        travelRequestOutput.setOrigin(travelRequest.getOrigin());
        travelRequestOutput.setDestination(travelRequest.getDestination());
        travelRequestOutput.setStatus(travelRequest.getStatus());
        travelRequestOutput.setId(travelRequest.getId());

        return travelRequestOutput;
    }

    public EntityModel<TravelRequestOutput> buildOutPutModel(TravelRequest travelRequest, TravelRequestOutput output){
        EntityModel<TravelRequestOutput> model = new EntityModel<>(output);
        Link passengerLink = WebMvcLinkBuilder
                .linkTo(PassengerAPI.class)
                .slash(travelRequest.getPassenger().getId())
                .withRel("passenger")
                .withTitle(travelRequest.getPassenger().getName());
        model.add(passengerLink);
        return model;
    }
}
