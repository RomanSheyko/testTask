package com.tempsensor.sensor.controllers;

import com.tempsensor.sensor.dblayer.SensorStorage;
import com.tempsensor.sensor.dblayer.SensorStorageRepository;
import com.tempsensor.sensor.dblayer.WorkingWithDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;


@Controller
public class ValidationController {
    @Autowired
    private SensorStorageRepository repository;

    @GetMapping
    public String main() {
        return "index";
    }

    @GetMapping("post")
    public String post(Map<String, Object> model) {
        populate(30);
        model.put("msg", "");
        return "post";
    }

    @PostMapping("post")
    public String add(@RequestParam String temp, @RequestParam String longitude, @RequestParam String latitude,
                      Map<String, Object> model) {
        String msg = addToBD(temp, longitude, latitude);
        model.put("msg", msg);
        return "post";
    }

    private String addToBD(String temp, String longitude, String latitude) {
        try {
            temp = temp.trim();
            longitude = longitude.trim();
            latitude = latitude.trim();
            double t = Double.parseDouble(temp);
            double lg = Double.parseDouble(longitude);
            double lt = Double.parseDouble(latitude);
            if(t > 0 || t < 100000) {
                SensorStorage sensor = repository.findFirstByLongitudeAndLatitude(longitude, latitude);
                if(sensor == null) sensor = new SensorStorage(temp, longitude, latitude);
                else sensor.setTemperature(temp);
                repository.save(sensor);
                return "sensor successfully added";
            } else return "wrong temperature";
        } catch(NumberFormatException e) {
            return "invalid input!";
        }
    }

    @GetMapping("get")
    public String show(Map<String, Object> model) {
        Iterable<SensorStorage> sensors = repository.findAll();
        model.put("sensors", sensors);

        return "get";
    }

    private void populate(int depth) {
        WorkingWithDB pop = new WorkingWithDB(repository);
        pop.populateDB(depth);
    }
}
