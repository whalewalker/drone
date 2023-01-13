package com.demo.data.repository;

import com.demo.data.model.LoadMedication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LoadDroneRepository extends JpaRepository<LoadMedication, String> {

    @Query("SELECT e FROM LoadMedication e WHERE e.drone.serialNumber = :serialNumber")
    LoadMedication findByDrone(@Param("serialNumber") String serialNumber);

    @Query("SELECT e FROM LoadMedication e WHERE e.medication.code = :code")
    LoadMedication findByCode(@Param("code") String code);
}
