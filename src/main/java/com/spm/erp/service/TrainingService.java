package com.spm.erp.service;

import java.util.List;
import java.util.Optional;

import com.spm.erp.model.CustomResponse;
import com.spm.erp.model.Training;

public interface TrainingService {
	
	List<Training> fetchAllTrainings();
	
	Optional<Training> findTrainingById(Long trainingId);
	
	CustomResponse insertTraining(Training training);
	
	CustomResponse updateTraining(Training training, Long trainingId);
	
	void deleteTraining(Long trainingId);

}
