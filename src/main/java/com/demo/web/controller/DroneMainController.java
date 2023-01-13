package com.demo.web.controller;

import com.demo.service.DroneServiceImpl;
import com.demo.web.exception.DroneException;
import com.demo.web.payload.request.DroneDeliveryRequest;
import com.demo.web.payload.request.DroneGetBatteryRequest;
import com.demo.web.payload.request.DroneRegisterRequest;
import com.demo.web.payload.request.LoadDroneRequest;
import com.demo.web.payload.response.*;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/drone")
@RequiredArgsConstructor
public class DroneMainController {
	private final DroneServiceImpl droneService;

	@PostMapping("/register")
	public ResponseEntity<RegisterDroneResponse> registerDrone(
			@Valid @RequestBody DroneRegisterRequest droneRegisterRequest) {
		RegisterDroneResponse newDrone = droneService.register(droneRegisterRequest);
		return new ResponseEntity<>(newDrone, HttpStatus.CREATED);
	}

	@GetMapping( "/available")
	public ResponseEntity<AvailableDroneResponse> getAvailableDroneForLoading() {
		AvailableDroneResponse drones = droneService.getAvailableDrones();
		return new ResponseEntity<>(drones, HttpStatus.OK);
	}

	@PostMapping("/battery")
	public ResponseEntity<DroneBatteryDetailsResponse> checkDroneBattery(
			 @RequestBody @Valid DroneGetBatteryRequest droneGetBatteryRequest) {
		DroneBatteryDetailsResponse droneBattery = droneService.getBatteryLevel(droneGetBatteryRequest);
		return new ResponseEntity<>(droneBattery, HttpStatus.CREATED);
	}

	@PostMapping("/load")
	public ResponseEntity<LoadDroneResponse> loadDroneWithMedication(@Valid  @RequestBody LoadDroneRequest droneRequest) {
		LoadDroneResponse loadDrone = droneService.loadDrone(droneRequest);
		return new ResponseEntity<>(loadDrone, HttpStatus.CREATED);
	}

	@GetMapping("details/{serialNumber}")
	public ResponseEntity<DroneMedicationLoadResponse> checkLoadedMedicationItem(@PathVariable("serialNumber") String serialNumber) {
		DroneMedicationLoadResponse droneLoads = droneService.getLoadedMedicationForADrone(serialNumber);
		return new ResponseEntity<>(droneLoads, HttpStatus.OK);
	}

	@PostMapping("/deliver")
	public ResponseEntity<DeliverDroneResponse> droneMedicalLoadDelivery( @RequestBody DroneDeliveryRequest deliverRequest) {
		DeliverDroneResponse delivery = droneService.deliverLoad(deliverRequest);
		return new ResponseEntity<>(delivery, HttpStatus.CREATED);
	}

}
