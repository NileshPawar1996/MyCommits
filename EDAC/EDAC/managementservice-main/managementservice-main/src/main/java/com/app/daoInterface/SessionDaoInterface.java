package com.app.daoInterface;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.app.pojos.Session;
import com.app.dtos.session.SessionInDescOrderDto;
import com.app.pojos.Module;
import java.util.List;

@Repository
public interface SessionDaoInterface extends JpaRepository<Session, Long>{
//	@Query(value = "SELECT * FROM session WHERE module", nativeQuery = true)
	List<Session> findByModule(Module module);
	
	@Query(value="SELECT * FROM sessions WHERE fk_module_id = ?1 and fk_batch_id = ?2", nativeQuery = true)
	List<Session> findByCourseAndBatch(Long module, Long batchId);
	
	@Query(value="SELECT * FROM sessions WHERE fk_batch_id = ?1", nativeQuery = true)
	List<Session> getSessionByBatchId(Long id);
	
	@Query(value="SELECT * FROM sessions WHERE fk_module_id = ?1 AND fk_batch_id = ?2", nativeQuery = true)
	List<Session> getSessionByModuleIdAndBatchId(Long id, Long batchId);
	
	@Query(value="SELECT * FROM management_db.sessions ORDER BY session_id DESC LIMIT ?1", nativeQuery = true)
	List<Session> getSessionInDescendingOrder(int numberOfSessions);
}
