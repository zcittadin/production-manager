package zan.ind.productionmanager.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import zan.ind.productionmanager.model.Batch;
import zan.ind.productionmanager.repository.BatchRepository;

@RestController
@RequestMapping("/product-manager")
public class BatchController {

	@Autowired
	BatchRepository batchRepository;

	@GetMapping("/batches")
	public List<Batch> getAllBatches() {
		return batchRepository.findAll();
	}

	@PostMapping("/batches")
	public Batch createBatch(@Valid @RequestBody Batch batch) {
		return batchRepository.save(batch);
	}

	@GetMapping("/batches/{id}")
	public ResponseEntity<Batch> getBatchById(@PathVariable(value = "id") Long batchId) {
		Batch batch = batchRepository.findOne(batchId);
		if (batch == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(batch);
	}

	@PutMapping("/batches/{id}")
	public ResponseEntity<Batch> updateBatch(@PathVariable(value = "id") Long batchId,
			@Valid @RequestBody Batch batchDetails) {
		Batch batch = batchRepository.findOne(batchId);
		if (batch == null) {
			return ResponseEntity.notFound().build();
		}
		batch.setNumber(batchDetails.getNumber());
		// batch.setNumber(batchDetails.getNumber());

		Batch updatedBatch = batchRepository.save(batch);
		return ResponseEntity.ok(updatedBatch);
	}

	@DeleteMapping("/batches/{id}")
	public ResponseEntity<Batch> deleteBatch(@PathVariable(value = "id") Long batchId) {
		Batch batch = batchRepository.findOne(batchId);
		if (batch == null) {
			return ResponseEntity.notFound().build();
		}

		batchRepository.delete(batch);
		return ResponseEntity.ok().build();
	}

}
