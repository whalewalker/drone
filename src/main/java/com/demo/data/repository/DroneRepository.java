package com.demo.data.repository;

import com.demo.data.model.Drone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Transactional
public interface DroneRepository extends JpaRepository<Drone, String> {

    List<Drone> findAllByState(String state);
    Optional<Drone> findBySerialNumber(String id);

    @Modifying
    @Query("update Drone d set d.state = :status where d.serialNumber = :serialNumber")
    void updateDroneState(@Param("status") String status, @Param("serialNumber") String serialNumber);
}
