package com.app.daoInterface;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.app.pojos.Batch;
import java.lang.String;
import java.util.List;
import java.lang.Long;

@Repository
public interface BatchDaoInterface extends JpaRepository<Batch, Long>{
//	@Query(nativeQuery = true, value = "SELECT * FROM batches WHERE batch_id = ?1")
//	Batch findByBatchId(String batch);
	Batch findByBatchId(String batchId);
	
	@Query(nativeQuery = true, value = "SELECT * FROM batches WHERE fk_course_id = ?1")
	List<Batch> findByCourse(String courseId);
}
