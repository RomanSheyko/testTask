package com.tempsensor.sensor.dblayer;

import org.springframework.beans.factory.annotation.Autowired;

public class WorkingWithDB {
    private SensorStorageRepository repository;

    public WorkingWithDB(SensorStorageRepository repository) {
        this.repository = repository;
    }

    public void populateDB(int depth) {
        SensorStorage sensor;
        int data_max = 180, data_min = -180, temp_min = 0, temp_max = 300;
        for(int i = 0;i < depth; i++) {
            sensor = new SensorStorage(String.valueOf(temp_min + (int)(Math.random()*temp_max)),
                    String.valueOf((data_min + (int)(Math.random()*data_max)) + Math.random()),
                    String.valueOf((data_min + (int)(Math.random()*data_max)) + Math.random()));
            repository.save(sensor);
        }
    }
}
