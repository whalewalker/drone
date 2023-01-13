package com.demo.data.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoadMedication {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private  Integer trackingId;

	private  String source;

	private  String destination;

	private  LocalDateTime createdAt;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_serial_number")
	private  Drone drone;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_code", unique = true)
	private  Medication medication;

}
