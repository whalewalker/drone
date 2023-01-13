package com.demo.service;

import com.demo.web.payload.request.DroneDeliveryRequest;
import com.demo.web.payload.request.DroneGetBatteryRequest;
import com.demo.web.payload.request.DroneRegisterRequest;
import com.demo.web.payload.request.LoadDroneRequest;
import com.demo.web.payload.response.*;
import org.springframework.stereotype.Component;

@Component
public interface DroneService {
	
	RegisterDroneResponse register(DroneRegisterRequest drone);

	DroneBatteryDetailsResponse getBatteryLevel(DroneGetBatteryRequest droneGetBatteryRequest);
	
	DroneMedicationLoadResponse getLoadedMedicationForADrone(String serialNo);
	
	AvailableDroneResponse getAvailableDrones();
	
	LoadDroneResponse loadDrone(LoadDroneRequest loadRequest);
	
	DeliverDroneResponse deliverLoad(DroneDeliveryRequest loadRequest);
	
}
