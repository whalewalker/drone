package com.demo.web.payload.response;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class DeliverDroneResponse {
	private String result;
	private String serialNumber;
	private String message;
	private LocalDateTime timestamp;
}
