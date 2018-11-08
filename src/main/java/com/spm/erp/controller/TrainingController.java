package com.spm.erp.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spm.erp.model.CustomResponse;
import com.spm.erp.model.Training;
import com.spm.erp.service.TrainingService;

@RestController
@RequestMapping("/trainings")
public class TrainingController {

	@Autowired
	TrainingService trainingService;

	@GetMapping("/training")
	public ResponseEntity<?> getAllTrainings() {
		return ResponseEntity.ok(trainingService.fetchAllTrainings());
	}

	@GetMapping("/training/{training_id}")
	public ResponseEntity<?> getTrainingById(@PathVariable("training_id") Long id) {
		Optional<Training> training = trainingService.findTrainingById(id);
		return ResponseEntity.ok(training.isPresent() ? training.get()
				: new CustomResponse(Boolean.FALSE, "No training found by ID: " + id));
	}

	@DeleteMapping("/training/{training_id}")
	public ResponseEntity<?> deleteTraining(@PathVariable("training_id") Long id) {
		Optional<Training> training = trainingService.findTrainingById(id);
		if (!training.isPresent()) {
			return ResponseEntity.ok(new CustomResponse(Boolean.FALSE, "No training found with id:" + id));
		}
		trainingService.deleteTraining(id);
		return ResponseEntity.ok(new CustomResponse(Boolean.TRUE, "Training deleted successfully."));
	}	

	@PostMapping("/training")
	public ResponseEntity<?> insertTraining(@Valid @RequestBody Training training) {
		CustomResponse response = trainingService.insertTraining(training);
		return ResponseEntity.ok(response);
	}

}
