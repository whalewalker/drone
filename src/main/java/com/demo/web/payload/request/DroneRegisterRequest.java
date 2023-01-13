package com.demo.web.payload.request;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DroneRegisterRequest {

	@NotBlank
	private String serialNumber;

	@NotBlank
	private String model;

	@NotNull
	private double weightLimit;

	@NotNull
	private BigDecimal battery;

	@NotBlank
	private String state;

}
