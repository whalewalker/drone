package com.demo.data.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class MedicalDelivery {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;

	private LocalDateTime deliveryTime;

	@OneToOne(targetEntity = LoadMedication.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_tracking_id")
	private LoadMedication loadMedication;

}
