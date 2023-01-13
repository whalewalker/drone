package com.demo.data.repository;

import com.demo.data.model.Medication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MedicationRepository extends JpaRepository<Medication, String> {

    @Query("SELECT e FROM Medication e WHERE e.code = :code")
    Medication findByCode(@Param("code") String code);

}
