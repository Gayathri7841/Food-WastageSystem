package com.example.SDP_project.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SDP_project.Model.Admin;
import com.example.SDP_project.Model.Donar;
import com.example.SDP_project.Model.Donations;
import com.example.SDP_project.Model.Recipient;
import com.example.SDP_project.Repository.AdminRepository;
import com.example.SDP_project.Repository.DonarRepository;
import com.example.SDP_project.Repository.DonationRepository;
import com.example.SDP_project.Repository.RecipientRepository;
@Service
public class AdminServiceImpl implements AdminService{
@Autowired
private AdminRepository repo;
@Autowired
private DonarRepository repo1;
@Autowired
private DonationRepository repo2;
@Autowired
private RecipientRepository repo3;

	@Override
	public Admin register(Admin admin) {
		return repo.save(admin);
	}

	@Override
	public Admin login(String email, String password) {
		return repo.findByEmailAndPassword(email,password);
	}

	@Override
	public List<Donar> getAllDonars() {
		List<Donar> donars = repo1.findAll();
	    System.out.println("Fetched Donars: " + donars); // Debugging
	    return donars;
	}

	@Override
	public List<Donations> getAllDonations() {
		return repo2.findAll();
	}

	@Override
	public List<Recipient> getAllRecipient() {
		return repo3.findAll();
	}

	public int countTotalDonations() {
	    return repo2.countAllDonations(); // Call the repository method
	}

	@Override
	public int countAllDonars() {
		System.out.println("Fetching donars");
		return repo1.countAllDonars();
		
	}

	@Override
	public int countAllRecipients() {
		return repo3.countAllRecipients();
	}


	


}
