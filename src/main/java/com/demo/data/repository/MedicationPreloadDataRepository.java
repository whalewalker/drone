package com.demo.data.repository;

import com.demo.data.model.Medication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicationPreloadDataRepository extends JpaRepository<Medication, String> {

	
	
}
