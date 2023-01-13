package com.demo.web.payload.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
public class LoadDroneResponse {
	private String result;
	private String serialNumber;
	private String message;
	private LocalDateTime timestamp;
}
