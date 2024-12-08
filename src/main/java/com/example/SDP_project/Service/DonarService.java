package com.example.SDP_project.Service;

import java.util.List;

import com.example.SDP_project.Model.Donar;
import com.example.SDP_project.Model.Donations;


public interface DonarService {
	public Donar DonarRegistration(Donar donar);
	public Donar DonarLoginVerification(String email,String password);
	public List<Donar> VerifyDuplicates(String email,String contactno);
	public String getfullname(String email);
	
	
	public List<Donar> getDonar(String email);
	public void updateotp(String otp,String email);
	public String getotp(String email);
	public void updatepassword(String password,String email);
	//Donations
	
	public Donations DonationsFormSubmission(Donations donations);
	public List<Donations> getDonationsByEmail(String email);
	 //Admin
	 public void updateDonationStatus(int id, String status);
	 
	 Donations findDonationById(int id);
	 Donations save(Donations donation);
	 long getAcceptedDonationsCount();
	 void assignDonationToAgent(int donationId, int agentId);
	 Donations getDonationDetails(Long id);
	 void updateDonation(Donations donation);
}
