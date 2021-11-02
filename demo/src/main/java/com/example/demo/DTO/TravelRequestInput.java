package com.example.demo.DTO;

import com.example.demo.domain.Passenger;
import lombok.Data;

@Data
public class TravelRequestInput {
    Long passengerId;
    String origin;
    String destination;
}
