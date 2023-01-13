package com.demo.service;

import com.demo.data.model.Drone;
import com.demo.data.model.LoadMedication;
import com.demo.data.model.MedicalDelivery;
import com.demo.data.model.Medication;
import com.demo.data.repository.DroneDeliveryRepository;
import com.demo.data.repository.DroneRepository;
import com.demo.data.repository.LoadDroneRepository;
import com.demo.data.repository.MedicationRepository;
import com.demo.web.exception.DroneException;
import com.demo.web.payload.request.DroneDeliveryRequest;
import com.demo.web.payload.request.DroneGetBatteryRequest;
import com.demo.web.payload.request.DroneRegisterRequest;
import com.demo.web.payload.request.LoadDroneRequest;
import com.demo.web.payload.response.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DroneServiceImpl implements DroneService {

	private final DroneRepository droneRepository;
	private final MedicationRepository medicationRepository;
	private final LoadDroneRepository loadDroneRepository;
	private final DroneDeliveryRepository droneDeliveryRepository;
	final String SUCCESS_MESSAGE = "success";

	@Override
	public RegisterDroneResponse register(DroneRegisterRequest droneRequest) {
		Drone newDrone = new Drone();
		newDrone.setSerialNumber(droneRequest.getSerialNumber());
		newDrone.setModel(droneRequest.getModel());
		newDrone.setWeightLimit(droneRequest.getWeightLimit());
		newDrone.setBattery(droneRequest.getBattery());
		newDrone.setState(droneRequest.getState());
		droneRepository.save(newDrone);
		

		RegisterDroneResponse droneResponse = new RegisterDroneResponse();
		droneResponse.setResult(SUCCESS_MESSAGE);
		droneResponse.setSerialNumber(newDrone.getSerialNumber());
		droneResponse.setMessage("New Drone created successfully");
		droneResponse.setTimestamp(LocalDateTime.now());

		return droneResponse;
	}

	@Override
	public AvailableDroneResponse getAvailableDrones() {
		String state = "IDLE";
		List<Drone> drones = droneRepository.findAllByState(state);
		return new AvailableDroneResponse("status", LocalDateTime.now(), drones);
	}

	@Override
	public DroneBatteryDetailsResponse getBatteryLevel(DroneGetBatteryRequest droneGetBatteryRequest) {

		Drone newDrone = new Drone();
		DecimalFormat decFormat = new DecimalFormat("#%");
		DroneBatteryDetailsResponse batteryDetailsResponse = new DroneBatteryDetailsResponse();
		newDrone.setSerialNumber(droneGetBatteryRequest.getSerialNumber());
		Drone droneBattery = droneRepository.findBySerialNumber(newDrone.getSerialNumber())
				.orElseThrow(()-> new DroneException("Drone battery level details not found"));

		batteryDetailsResponse.setStatus(SUCCESS_MESSAGE);
		batteryDetailsResponse.setSerialNumber(droneBattery.getSerialNumber());
		batteryDetailsResponse.setBattery(decFormat.format(droneBattery.getBattery()));
		batteryDetailsResponse.setTimestamp(LocalDateTime.now());

		return batteryDetailsResponse;
	}

	@Override
	public DroneMedicationLoadResponse getLoadedMedicationForADrone(String serialNo) {

		LoadMedication loadMedication = loadDroneRepository.findByDrone(serialNo);
		if(loadMedication==null) {
			throw new DroneException("No load Medication details for the specified drone");
		}
		DroneMedicationLoadResponse droneLoad = new DroneMedicationLoadResponse();
		droneLoad.setResult(SUCCESS_MESSAGE);
		droneLoad.setSerialNumber(loadMedication.getDrone().getSerialNumber());
		droneLoad.setTimestamp(LocalDateTime.now());
		droneLoad.setMedication(loadMedication.getMedication());

		return droneLoad;
	}

	@Override
	public LoadDroneResponse loadDrone(LoadDroneRequest loadRequest) {
		droneRepository.updateDroneState("LOADING", loadRequest.getSerialNumber());
		Drone drone = droneRepository.findBySerialNumber(loadRequest.getSerialNumber())
				.orElseThrow(()-> new DroneException("Drone battery level details not found"));

		Medication medication = medicationRepository.findByCode(loadRequest.getCode());
		LoadMedication checkLoad = loadDroneRepository.findByCode(loadRequest.getCode());
		
		if(checkLoad != null) {
			throw new DroneException("Medication code has already been loaded, try another one");
		}

		// validate before loading
		if (drone == null) {
			throw new DroneException("Drone specified does not exist");
		}

		if (medication == null) {
			throw new DroneException("Medication specified does not exist");
		}

		if (drone.getWeightLimit() < medication.getWeight()) {
			throw new DroneException("The Drone cannot load more than the weight limit");
		}  
		// check battery
			if( drone.getBattery().compareTo(BigDecimal.valueOf(0.25)) < 0  ){
				throw new DroneException("The Drone cannot be loaded, battery below 25%");
			}

		// load
		LoadMedication loadMedication = new LoadMedication();
		loadMedication.setDrone(drone);
		loadMedication.setMedication(medication);
		loadMedication.setSource(loadRequest.getSource());
		loadMedication.setDestination(loadRequest.getDestination());
		loadMedication.setCreatedAt(LocalDateTime.now());
		loadDroneRepository.save(loadMedication);
		droneRepository.updateDroneState("LOADED", loadRequest.getSerialNumber());

		LoadDroneResponse loadDroneResponse = new LoadDroneResponse();
		loadDroneResponse.setResult(SUCCESS_MESSAGE);
		loadDroneResponse.setSerialNumber(loadRequest.getSerialNumber());
		loadDroneResponse.setMessage("Drone Loaded successfully");
		loadDroneResponse.setTimestamp(LocalDateTime.now());

		return loadDroneResponse;
	}

	@PostConstruct
	private void preloadMedicationDB() {
		List<Medication> medications = new ArrayList<>(List.of(
				new Medication("WE232344","Covax",100,"sade23Rd"),
				new Medication("WE232345","Meloxicam",150,"sade2Y4d"),
				new Medication("WE232346","Metformin",200,"sade2U4d"),
				new Medication("WE232346","Metformin",200,"sade2U4d"),
				new Medication("WE232347","Acetaminophen",300,"sade2Q4d"),
				new Medication("WE232348","Amoxicillin",400,"sa3e234d"),
				new Medication("WE232349","Ativan",260,"sade237d"),
				new Medication("WE2323510","Atorvastatin",180,"sade2F4d"),
				new Medication("WE2323511","Azithromycin",400,"sade2B4d"),
				new Medication("WE2323511","Azithromycin",400,"sade2B4d"),
				new Medication("WE2323512","Zyloprim",400,"sadeH34d"),
				new Medication("WE2323513","Diprolene ",400,"sade234J")
		));
		medicationRepository.saveAll(medications);
	}

	@Override
	public DeliverDroneResponse deliverLoad(DroneDeliveryRequest loadRequest) {
		droneRepository.updateDroneState("DELIVERING", loadRequest.getSerialNumber());
		LoadMedication loadMedication = loadDroneRepository.findByDrone(loadRequest.getSerialNumber());
		
		if(loadMedication==null) {
			throw new DroneException("Drone specifies is not loaded or does not exist");
		}
		
		MedicalDelivery newDelivery = new MedicalDelivery();
		newDelivery.setLoadMedication(loadMedication);
		newDelivery.setDeliveryTime(LocalDateTime.now());
		droneDeliveryRepository.save(newDelivery);
		droneRepository.updateDroneState("DELIVERED", loadRequest.getSerialNumber());

		DeliverDroneResponse deliverDroneResponse = new DeliverDroneResponse();
		deliverDroneResponse.setResult(SUCCESS_MESSAGE);
		deliverDroneResponse.setSerialNumber(loadRequest.getSerialNumber());
		deliverDroneResponse.setMessage("Delivered successfully");
		deliverDroneResponse.setTimestamp(LocalDateTime.now());

		return deliverDroneResponse;
	}
}
