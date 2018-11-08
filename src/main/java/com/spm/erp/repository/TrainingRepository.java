package com.spm.erp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spm.erp.model.Training;

@Repository
public interface TrainingRepository extends JpaRepository<Training, Long> {	
	
	Boolean existsByTitle(String title);

}
