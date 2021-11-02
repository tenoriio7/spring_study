package com.example.demo.interfaces;

import com.example.demo.domain.TravelRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TravelRequestRepository extends JpaRepository<TravelRequest,Long> {
}
