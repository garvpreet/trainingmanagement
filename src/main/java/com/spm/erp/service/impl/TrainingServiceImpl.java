package com.spm.erp.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spm.erp.exception.AppException;
import com.spm.erp.model.CustomResponse;
import com.spm.erp.model.Training;
import com.spm.erp.repository.TrainingRepository;
import com.spm.erp.service.TrainingService;

@Service
public class TrainingServiceImpl implements TrainingService{
	
	@Autowired
	TrainingRepository trainingRepo;

	@Override
	public List<Training> fetchAllTrainings() {		
		return trainingRepo.findAll();
	}
	
	@Override
	public Optional<Training> findTrainingById(Long trainingId) {		
		return trainingRepo.findById(trainingId);
	}

	@Override
	public CustomResponse insertTraining(Training training) {
		if (trainingRepo.existsByTitle(training.getTitle())) {
            return new CustomResponse(false, "Training with title "+ training.getTitle() + " is already taken!");
        }

        try {
        	trainingRepo.save(training);
            return new CustomResponse(true, "Training saved successfully");
        } catch (Exception ex) {
            throw new AppException("Training did not get saved.");
        }
	}

	@Override
	public boolean updateTraining(Training training, Long trainingId) {
		//TODO
		return false;
	}

	@Override
	public void deleteTraining(Long trainingId) {
		try {
			trainingRepo.deleteById(trainingId);
		} catch (Exception e) {
			throw new AppException("Training did not get deleted.");
		}
		
	}

}
