package com.tempsensor.sensor.controllers;

import com.tempsensor.sensor.dblayer.SensorStorage;
import com.tempsensor.sensor.dblayer.SensorStorageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {
    @Autowired
    private SensorStorageRepository repository;

    @GetMapping("/user")
    public Iterable<SensorStorage> getData() {
        return repository.findFirst10ByOrderByIdDesc();
    }
}
