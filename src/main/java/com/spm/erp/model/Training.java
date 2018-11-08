package com.spm.erp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "training")
public class Training {	

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 250)
    private String title;

    @NotBlank
    @Size(max = 100000)
    private String description;

    @NotNull
    private Double hours;
    
    @NotNull
    private Long job_id;

    public Training(String title, String description, Double hours, Long jobID) {
		this.title = title;
		this.description = description;
		this.hours = hours;
		this.job_id = jobID;
	}

}
