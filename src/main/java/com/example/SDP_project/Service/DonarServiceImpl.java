package com.example.SDP_project.Service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.SDP_project.Model.Donar;
import com.example.SDP_project.Model.Donations;
import com.example.SDP_project.Model.Notification;
import com.example.SDP_project.Repository.DonarRepository;
import com.example.SDP_project.Repository.DonationRepository;
import com.example.SDP_project.Repository.NotificationRepository;

@Service
public class DonarServiceImpl implements DonarService {
	@Autowired
private DonarRepository repo;
	@Autowired
	private DonationRepository repo1;
	@Autowired
	private NotificationRepository repo2;
	@Override
	public Donar DonarRegistration(Donar donar) {
		
		return repo.save(donar);
		
	}

	@Override
	public Donar DonarLoginVerification(String email, String password) {
		return repo.DonarLoginVerification(email, password);
	}
	public List<Donar> VerifyDuplicates(String email, String contactno)
	{
		return repo.VerifyDuplicates(email, contactno); 
	}
	public String getfullname(String email) 
	{
		return repo.getfullname(email);
	}

	
	@Override
	public List<Donar> getDonar(String email) {
		System.out.println("Donors fetched: " );
		return repo.getDonar(email);
		 
	}

	@Override
	public void updateotp(String otp, String email) {
		repo.SetOtp(otp,email);
		
	}

	@Override
	public String getotp(String email) {
		return repo.GetOtp(email);
	}

	@Override
	public void updatepassword(String password, String email) {
		repo.UpdatePassword(password,email);
		
	}

	@Override
	public Donations DonationsFormSubmission(Donations donation) {
		return repo1.save(donation);
	}

	

	



	public List<Donations> getDonationsByEmail(String email) {
		 System.out.println("Fetching donations for email: " + email);
        return repo1.findByEmail(email);
    }

	 @Transactional
	    public void updateDonationStatus(int id, String status) {
		 Donations donations=repo1.findById(id);
		 if (donations != null && "Pending".equalsIgnoreCase(donations.getStatus())) {
	            donations.setStatus(status);
	            repo1.save(donations); // Save the updated status
	        }
	        
	    }

	

	@Override
	public Donations findDonationById(int id) {
		return repo1.findById(id);
	}

	 public Donations save(Donations donation) {
	        return repo1.save(donation); // Saves donation to the database
	    }

	@Override
	public long getAcceptedDonationsCount() {
		  return repo1.countByStatus("Accepted");
	}

	@Override
	public void assignDonationToAgent(int donationId, int agentId) {
		Donations donation = repo1.findById(donationId);
        donation.setAssignedAgentId(agentId); // Assign agent
        repo1.save(donation);

        // Create and send notification
        Notification notification = new Notification();
        // Set the agent receiving the notification
        notification.setMessage("You have a new donation assigned!");
        // Link to the donation form
        repo2.save(notification);

        // Additional logging or actions if needed
        System.out.println("Notification sent to agent with ID: " + agentId);
    }

	@Override
	public Donations getDonationDetails(Long id) {
		 return repo1.findById(id);
	}

	@Override
	public void updateDonation(Donations donation) {
		repo1.save(donation);
		
	}
	
	}
	


