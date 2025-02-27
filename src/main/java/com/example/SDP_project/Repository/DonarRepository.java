package com.example.SDP_project.Repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.SDP_project.Model.Donar;


import jakarta.transaction.Transactional;

@Repository
public interface DonarRepository extends JpaRepository<Donar, Integer> {
	@Query("From Donar d where d.emailid=?1 and d.password=?2 or d.contactno=?1 and d.password=?2")
	public Donar DonarLoginVerification(String email,String password);
	
	@Query("From Donar d where d.emailid=?1  or d.contactno=?2 ")
	public List<Donar> VerifyDuplicates(String email,String contactno);
	@Query("Select d.fullname from Donar d where d.emailid=?1 or d.contactno=?1")
	public String getfullname(String email);
	
	
	@Query("From Donar d where d.emailid=?1")
	public List<Donar> getDonar(String email);
	@Transactional
	@Modifying
	@Query("update Donar d set d.otp=?1 where d.emailid=?2")
	public void SetOtp(String otp,String email);
	
	@Query("Select d.otp from Donar d where d.emailid=?1")
	public String GetOtp(String email);
	
	@Transactional
	@Modifying
	@Query("update Donar d set d.password=?1 where d.emailid=?2")
	public void UpdatePassword(String password,String email);
	//Donation
	 @Query("SELECT COUNT(d) FROM Donar d")
	  int countAllDonars();
	 
	
}
