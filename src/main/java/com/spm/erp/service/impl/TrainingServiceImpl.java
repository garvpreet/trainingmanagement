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
import com.spm.erp.util.BeanUtil;

@Service
public class TrainingServiceImpl implements TrainingService {

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
			return new CustomResponse(false, "Training with title " + training.getTitle() + " is already taken!");
		}

		try {
			trainingRepo.save(training);
			return new CustomResponse(true, "Training saved successfully");
		} catch (Exception ex) {
			throw new AppException("Training did not get saved.");
		}
	}

	@Override
	public CustomResponse updateTraining(Training training, Long trainingId) {
		Optional<Training> trainingOptional = trainingRepo.findById(trainingId);
		if (!trainingOptional.isPresent())
			return new CustomResponse(false, "No training found by ID: " + trainingId);
		if (trainingRepo.existsByTitle(training.getTitle())) {
			return new CustomResponse(false, "Training with title " + training.getTitle() + " is already taken!");
		}
		Training trainingActual = trainingOptional.get();
		trainingActual = BeanUtil.<Training>copyNonNullProperties(trainingActual, training);
		try {
			trainingRepo.save(trainingActual);
			return new CustomResponse(true, "Training updated successfully");
		} catch (Exception ex) {
			throw new AppException("Something went wrong.Training details not updated.");
		}
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
