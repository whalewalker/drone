package com.demo.scheduler;

import com.demo.data.model.Drone;
import com.demo.data.repository.DroneRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

@EnableScheduling
@EnableAsync
@RequiredArgsConstructor
@Slf4j
public class ScheduledCheckDroneBattery {
	
	private final  DroneRepository droneRepository;

    @Scheduled(fixedRate = 5000)
    public void scheduleFixedRateTaskAsync() throws InterruptedException {
        List<Drone> arrDroneBatteryLevels = droneRepository.findAll();
        for (Drone arrDroneBatteryLeve : arrDroneBatteryLevels) {
            log.info("Battery level--: SerialNumber  {} Battery Level {}", arrDroneBatteryLeve.getSerialNumber(), arrDroneBatteryLeve.getBattery());
        }
        Thread.sleep(5000);
    }
    
}
