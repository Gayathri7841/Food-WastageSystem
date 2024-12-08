package com.example.SDP_project.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.SDP_project.Model.Admin;
import com.example.SDP_project.Model.Donar;
import com.example.SDP_project.Model.Donations;
import com.example.SDP_project.Model.Notification;
import com.example.SDP_project.Model.Recipient;
import com.example.SDP_project.Service.AdminService;
import com.example.SDP_project.Service.DonarService;
import com.example.SDP_project.Service.NotificationService;
import com.example.SDP_project.Service.RecipientService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AdminController  {
	@Autowired
	private AdminService service;
	@Autowired
	private DonarService service1;
	@Autowired
	private NotificationService service2;
	@Autowired
	private RecipientService service3;
	
	
	
	private void createNotificationForAgent(Donations donation, Recipient recipient, String message) {
	    // Create a new notification object
	    Notification notification = new Notification();
	    
	    // Set the necessary details for the notification
	          // Set the donation related to the notification
	    notification.setRecipient(recipient);      // Set the recipient (agent) who will receive the notification
	    notification.setMessage(message);          // Set the notification message
	             // Initial status of the notification
	    
	    // Save the notification using NotificationService
	    service2.saveNotification(notification);
	}

	//Admin Login*************************************************************************************************
	@GetMapping("/showAdminLogin")
	public String showLoginPage() {
		return "AdminLoginForm";
	}
	@PostMapping("/verifyAdminLogin")
	public String AdminLoginVerification(@RequestParam String email, @RequestParam String password,HttpSession session,Model model) {
		Admin admin=service.login(email, password);
	if(admin!=null) {
		session.setAttribute("admin", admin);
		return "redirect:/showAdminHome";
	}
	model.addAttribute("error","Invalid Email or Password");
	return "redirect:/showAdminLogin";
	}
	//Admin Registration**************************************************************************************************
	@GetMapping("/showAdminRegistration")
	public String  showAdminRegistrationPage() {
		return "AdminRegistrationForm";
	}
	@PostMapping("/registerAdmin")
	public String registerAdmin(@ModelAttribute Admin admin,Model model) {
		service.register(admin);
		model.addAttribute("message", "Admin Registered Successfully!");
		return "redirect:/showAdminLogin";
	}
	//Admin HomePage*******************************************************************************************************
	@GetMapping("/showAdminHome")
	public String showAdminHomePage(HttpSession session, Model model) {
	    Admin admin = (Admin) session.getAttribute("admin");
	    if (admin != null) {
	        model.addAttribute("adminName", admin.getFullname());

	        // Fetch donation and donor counts
	        int totalDonationsCount = service.countTotalDonations();
	        int totalDonarsCount = service.countAllDonars();
            int totalRecipientsCount=service.countAllRecipients();
            long acceptedDonationsCount = service1.getAcceptedDonationsCount();
	        // Add counts to the model
	        model.addAttribute("donationCount", totalDonationsCount);
	        model.addAttribute("donarCount", totalDonarsCount);
	        model.addAttribute("recipientsCount", totalRecipientsCount);
	        model.addAttribute("acceptedDonationsCount", acceptedDonationsCount);
	        return "AdminHomePage";
	    }
	    return "redirect:/showAdminLogin";
	}


	//Admin Logout*************************************************************************************************************
	@GetMapping("/logout")
	public String Logout(HttpSession session) {
		session.invalidate();
		return "redirect:/showAdminLogin";
	}
	//View all donars**********************************************************************************************************
	@GetMapping("/viewAllDonars")
	public String viewAllDonars(Model model) {
		List<Donar> donors=service.getAllDonars();
		 System.out.println("Donors passed to Thymeleaf: " + donors);
		model.addAttribute("donors", donors);
		return "ViewDonars";
	}
	
	@GetMapping("/viewAllDonations")
	public String viewAllDonations(Model model) {
		List<Donations> donations=service.getAllDonations();
		 List<Recipient> agents = service.getAllRecipient();
		 model.addAttribute("agents", agents);
		model.addAttribute("donations", donations);
		return "ViewDonations";
	}
	



//View Recipients************************************************************************************************************
	// View all recipients
	@GetMapping("/viewAllRecipients")
	public String viewAllRecipients(Model model) {
	    List<Recipient> recipients = service.getAllRecipient();
	    model.addAttribute("recipients", recipients);
	    return "ViewRecipients";
	}
//Count Donations**********************************************************************************************************
	@GetMapping("/totalDonations")
	public String getTotalDonations(Model model, HttpSession session) {
	    Admin admin = (Admin) session.getAttribute("admin");
	    if (admin != null) {
	        int totalDonationsCount = service.countTotalDonations();
	        model.addAttribute("adminName", admin.getFullname());
	        model.addAttribute("donationCount", totalDonationsCount);
	        return "AdminHomePage"; // Redirect to AdminHomePage with updated count
	    }
	    return "redirect:/showAdminLogin";
	}
//Count Donars****************************************************************************************************************
	@GetMapping("/totalDonars")
	public String getTotalDonars(Model model, HttpSession session) {
	    Admin admin = (Admin) session.getAttribute("admin");
	    if (admin != null) {
	       
	        model.addAttribute("adminName", admin.getFullname());
	        int totalDonarsCount = service.countAllDonars();
	        model.addAttribute("donarCount", totalDonarsCount);
	        return "AdminHomePage"; // Redirect to AdminHomePage with donor count
	    }
	    return "redirect:/showAdminLogin"; // Redirect to login if admin is not logged in
	}
//Count Recipients****************************************************************************************************************
	@GetMapping("/totalrecipients")
	public String getTotalRecipients(Model model, HttpSession session) {
	    Admin admin = (Admin) session.getAttribute("admin");
	    if (admin != null) {
	       
	        model.addAttribute("adminName", admin.getFullname());
	        int totalRecipientsCount = service.countAllRecipients();
	        model.addAttribute("recipientCount", totalRecipientsCount);
	        return "AdminHomePage"; // Redirect to AdminHomePage with donor count
	    }
	    return "redirect:/showAdminLogin"; // Redirect to login if admin is not logged in
	}
	
	


//update donation status*********************************************************************************************************
	// Update donation status
	@PostMapping("/updateDonationStatus")
	public ResponseEntity<Map<String, String>> updateDonationStatus(@RequestParam int id, 
	                                                                @RequestParam String status) {
	    try {
	        Donations donation = service1.findDonationById(id);
	        if (donation != null) {
	            // Update donation status
	            donation.setStatus(status);
	            service1.updateDonation(donation); // Assuming update method works correctly

	            // Create response to send to frontend
	            Map<String, String> response = new HashMap<>();
	            response.put("status", status); // Send the updated status back
	            return ResponseEntity.ok(response);
	        } else {
	            Map<String, String> response = new HashMap<>();
	            response.put("message", "Donation not found");
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	        }
	    } catch (Exception e) {
	        // Log error for troubleshooting
	        Map<String, String> response = new HashMap<>();
	        response.put("message", "Error processing donation");
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	    }
	}

//Process Donation****************************************************************************************************************
	@GetMapping("/processDonation/{id}")
	public String processDonation(@PathVariable int id, Model model) {
	    // Fetch the donation details based on the ID
	    Donations donation = service1.findDonationById(id);
	    if (donation == null) {
	        return "redirect:/error"; // Handle the error gracefully
	    }

	    // Fetch all recipients (this assumes recipientService is already injected)
	    List<Recipient> recipients = service.getAllRecipient(); 

	    // Add both donation and recipients to the model
	    model.addAttribute("donation", donation);
	    model.addAttribute("recipients", recipients);

	    return "ProcessDonationForm";  // Ensure this matches the name of your Thymeleaf template
	}


//submit processed donation
	@PostMapping("/submitProcessedDonation")
	public String submitProcessedDonation(
	        @RequestParam("recipient") Integer recipientId,
	        @RequestParam("donationId") int donationId,
	        @RequestParam("message") String message, // Capture the message input from the form
	        Model model) {

	    // Fetch the donation by ID
	    Donations donation = service1.findDonationById(donationId);
	    if (donation == null) {
	        return "redirect:/error";  // Handle error if donation is not found
	    }

	    // Fetch the recipient (agent) by ID
	    Recipient recipient = service3.findRecipientById(recipientId);
	    if (recipient == null) {
	        return "redirect:/error";  // Handle error if recipient is not found
	    }

	    // Update the donation's recipient, process status, and assigned agent ID
	    donation.setRecipient(recipient);
	    donation.setProcessStatus("In Progress");
	    donation.setAssignedAgentId(recipient.getId());

	    // Save the updated donation to the database
	    service1.save(donation);

	    // Set the message from the admin as the notification message
	    recipient.setNotificationMessage(message); // Use the message passed by the admin

	    // Save the updated recipient with the notification message
	    service3.save(recipient);

	    // Create a notification for the agent (using the message from the form)
	    Notification notification = new Notification();
	    notification.setMessage(message); // Set the message received from the form
	    notification.setRecipient(recipient);  // Associate the recipient
	    // Timestamp is set automatically by the @PrePersist annotation in the Notification entity

	    // Save the notification to the database
	    service2.save(notification);

	    // Add updated donation to the model
	    model.addAttribute("donation", donation);

	    // Redirect or return a success view
	    return "redirect:/processDonation/" + donationId;
	}
}