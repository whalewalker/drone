package com.demo.web.payload.response;

import com.demo.data.model.Medication;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class DroneMedicationLoadResponse {
    private String result;
    private String serialNumber;
    private LocalDateTime timestamp;
    private Medication medication;
}
