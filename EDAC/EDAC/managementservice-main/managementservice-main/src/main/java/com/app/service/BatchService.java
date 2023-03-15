package com.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.app.daoInterface.BatchDaoInterface;
import com.app.dtos.batch.ModuleCompletionDto;
import com.app.dtos.module.StatusAndTeacherNameDto;
import com.app.pojos.Batch;
import com.app.pojos.Module;


@Service
public class BatchService {
	@Autowired
	BatchDaoInterface batchDao;
	@Autowired
	ModuleService moduleService;
	
	public String addBatch(Batch batch) {
		batchDao.save(batch);
		return "success";
	}

	public List<Batch> getAllBatches() {
		return batchDao.findAll();
	}

	public Batch getByBatch(String batchId) {
		return batchDao.findByBatchId(batchId);
	}

	public Batch getById(Long id) {
		return batchDao.findById(id).orElse(null);
	}

	public List<Batch> getBatchesByCourseId(String courseId) {
		List<Batch> batches = batchDao.findByCourse(courseId);
		return batches;
	}

	public String deleteBatch(Long id) {
		if(batchDao.existsById(id)) {
			batchDao.deleteById(id);
			return "success";
		}
		else
			return "failed";
	}
	
}
