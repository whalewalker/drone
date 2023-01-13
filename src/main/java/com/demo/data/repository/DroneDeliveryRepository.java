package com.demo.data.repository;

import com.demo.data.model.MedicalDelivery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DroneDeliveryRepository extends JpaRepository<MedicalDelivery, String> {

}
