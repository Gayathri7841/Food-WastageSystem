package com.example.SDP_project.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import com.example.SDP_project.Model.Recipient;

@Repository
public interface RecipientRepository extends JpaRepository<Recipient, Integer> {

    @Query("FROM Recipient r WHERE r.email = ?1 AND r.password = ?2 OR r.contactno = ?1 AND r.password = ?2")
    public Recipient RecipientLoginVerification(String email, String password);

    @Query("FROM Recipient r WHERE r.email = ?1 OR r.contactno = ?2")
    public List<Recipient> VerifyDuplicates(String email, String contactno);

    Optional<Recipient> findByEmail(String email);
    @Query("SELECT COUNT(r) FROM Recipient r")
	  int countAllRecipients();
    Recipient findByName(String name);
    
    //Retrive recipient by id
    Optional<Recipient> findById(int id);
    
}
