package com.demo.web.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoadDroneRequest {

	@NotBlank
	private String serialNumber;
	
	@NotBlank
	private String source;
	
	@NotBlank
	private String destination;
	
	@NotBlank
	private String code;
}
