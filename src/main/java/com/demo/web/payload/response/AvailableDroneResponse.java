package com.demo.web.payload.response;

import com.demo.data.model.Drone;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@RequiredArgsConstructor
public class AvailableDroneResponse {

	private final String status;
	private final LocalDateTime timestamp;
	private final List<Drone> drones;
}
