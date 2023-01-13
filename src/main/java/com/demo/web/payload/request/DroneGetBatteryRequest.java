package com.demo.web.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DroneGetBatteryRequest {
	@NotBlank(message = "Serial Number cannot be blank")
	private String serialNumber;
}
