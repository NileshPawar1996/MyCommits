package com.app.daoInterface;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.dtos.admin.GetAdminDetails;
import com.app.pojos.Staff;

@Repository
public interface AdminDaoInterface extends JpaRepository<Staff, Long>{

	@Query(value = "SELECT * FROM staff WHERE staff_id = ?1 AND role = 'ADMIN';", nativeQuery = true)
	Staff findAdminById(Long id);

	@Query(value = "SELECT * FROM staff WHERE role = 'ADMIN';", nativeQuery = true)
	List<Staff> getAllAdmins();

}
