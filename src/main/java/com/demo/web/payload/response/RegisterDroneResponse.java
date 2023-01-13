package com.demo.web.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterDroneResponse {
	private  String result;
	private  String serialNumber;
	private  String message;
	private  LocalDateTime timestamp;
}
