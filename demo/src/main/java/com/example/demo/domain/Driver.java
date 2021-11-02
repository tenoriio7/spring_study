package com.example.demo.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;


@Data
@Entity
public class Driver
{
    @Id
    @GeneratedValue
    Long id;
    String name;
    Date birthDate;
}
