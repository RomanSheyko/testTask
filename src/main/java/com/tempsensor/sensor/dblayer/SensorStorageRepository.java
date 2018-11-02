package com.tempsensor.sensor.dblayer;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorStorageRepository extends CrudRepository<SensorStorage, Long> {

    Iterable<SensorStorage> findFirst10ByOrderByIdDesc();
    SensorStorage findFirstByLongitudeAndLatitude(String longitude, String latitude);
}
