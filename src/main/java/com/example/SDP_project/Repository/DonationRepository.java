package com.example.SDP_project.Repository;


import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.SDP_project.Model.Donations;



@Repository
public interface DonationRepository extends JpaRepository<Donations, Integer> {
	
	  @Query("SELECT d FROM Donations d WHERE d.email = :email")
    List<Donations> findByEmail(String email);
	  @Modifying
	    @Query("UPDATE Donations d SET d.status = :status WHERE d.id = :id")
	    void updateDonationStatus(int id, String status);
	  @Query("SELECT COUNT(d) FROM Donations d")
	  int countAllDonations();


	  List<Donations> findByRecipientId(int recipientId);
	  long countByStatus(String status); 
	  Donations findById(long id); 
}
