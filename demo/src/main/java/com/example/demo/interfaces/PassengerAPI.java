package com.example.demo.interfaces;

import com.example.demo.domain.Passenger;
import com.example.demo.domain.PassengerRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RestController
@RequestMapping(path = "/passengers", produces = MediaType.APPLICATION_JSON_VALUE)
public class PassengerAPI {

    @Autowired
    PassengerRespository passengerRespository;

    @GetMapping
    public List<Passenger> listPassengers(){
        return passengerRespository.findAll();
    }

    @GetMapping("/{id}")
    public Passenger findPassenger(@PathVariable("id") Long id){
        return passengerRespository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/{id}")
    public Passenger createPassenger(@RequestBody Passenger passenger){
        return passengerRespository.save(passenger);
    }

    @PutMapping("/{id}")
    public  Passenger fullUpdatePassenger(@PathVariable("id") Long id, @RequestBody Passenger passenger){
        Passenger passenger_aux = findPassenger(id);
        passenger_aux.setName(passenger.getName());
        return passengerRespository.save(passenger_aux);
    }

    @PatchMapping("/{id}")
    public Passenger incrementalUpdatePassenger(@PathVariable("id") Long id, @RequestBody Passenger passenger){
        Passenger foundPassenger =  findPassenger(id);
        foundPassenger.setName(Optional.ofNullable(passenger.getName()).orElse(foundPassenger.getName()));
        return passengerRespository.save(foundPassenger);
    }

    @DeleteMapping("/{id}")
    public void deletePassenger(@PathVariable("id") Long id){
        passengerRespository.delete(findPassenger(id));
    }



}
