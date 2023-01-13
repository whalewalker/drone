package com.demo.data.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Drone {
	@Id
	@Column(unique = true)
	private String serialNumber;

	private String model;
	
	private double weightLimit;

	private BigDecimal battery;

	private String state;
}
