package com.example.SDP_project.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.http.HttpSession;

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
import org.springframework.web.servlet.ModelAndView;

import com.example.SDP_project.Model.Donar;
import com.example.SDP_project.Model.Donations;
import com.example.SDP_project.Service.DonarService;
import com.example.SDP_project.Service.EmailSendingService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class DonarController {
	@Autowired
	private DonarService service;
	@Autowired
	private EmailSendingService senderService;
	@GetMapping("/showDonarHome")
	public ModelAndView showDonarHomePage(HttpSession session) {
	    ModelAndView mv = new ModelAndView();
	    Donar donar = (Donar) session.getAttribute("donar");

	    if (donar != null) {
	        String fullname = donar.getFullname();
	       

	        mv.addObject("fullname", fullname);
	       
	        mv.setViewName("DonarHomePage");
	    } else {
	        mv.setViewName("redirect:/showLoginForm");
	    }

	    return mv;
	}



	
	@GetMapping("/home")
public String showHomePage() {
	return "HomePage";
}
	@GetMapping("/showRoles")
	public ModelAndView Roles() {
		ModelAndView mv=new ModelAndView();
		mv.setViewName("Roles");
		return mv;
	}
	@GetMapping("/showLoginForm")
	public ModelAndView LoginForm() {
		ModelAndView mv=new ModelAndView();
	
				mv.setViewName("Login");
				return mv;
	}
	@PostMapping("/DonarLoginVerification")
	public ModelAndView DonarLoginVerification(HttpServletRequest request) {
	    ModelAndView mv = new ModelAndView();    
	    String email = request.getParameter("donaremail");
	    String password = request.getParameter("donarpassword");

	    // Verify login credentials
	    Donar donar = service.DonarLoginVerification(email, password);
	    
	    if (donar != null) {
	        // Store the Donar object in the session
	        HttpSession session = request.getSession();
	        session.setAttribute("donar", donar); // Storing the full Donar object
	        
	        mv.setViewName("redirect:/showDonarHome"); // Redirect to DonarHomePage
	    } else {
	        mv.setViewName("Login");
	        mv.addObject("msg", "Incorrect Credentials");
	    }
	    
	    return mv;
	}


@GetMapping("/showDonarRegistration")
public  ModelAndView DonarRegistrationForm() {
	ModelAndView mv=new ModelAndView();
	mv.setViewName("DonarRegistrationForm");
	return mv;
	
}
@PostMapping("/DonarRegistration")
public ModelAndView DonarRegistration(@ModelAttribute("donar")Donar donar,HttpServletRequest request) {
	ModelAndView mv=new ModelAndView();
	List<Donar> donars=service.VerifyDuplicates(donar.getEmailid(), donar.getContactno());
	if(donars.size()>0) {
		mv.setViewName("DonarRegistrationForm");
		mv.addObject("duplicateerror","Duplicate Account");
	}
	else {
		service.DonarRegistration(donar);
		mv.setViewName("redirect:/showLoginForm");
		
	}
	return mv;
}
@GetMapping("/showDonationForm")
public String DonationForm(Model model)
{
	model.addAttribute("donations",new Donations());
	return "DonationForm";
}
@PostMapping("/Donations")
public String DonationsForm(@ModelAttribute Donations donations)
{ 	 	
	
	service. DonationsFormSubmission(donations);
	
	return "DonationFormSuccess";
	
}
@GetMapping("/showDonarsDonations")
public String viewDonationsByEmail(HttpSession session, Model model) {
    Donar donar = (Donar) session.getAttribute("donar");

    if (donar != null) {
        String email = donar.getEmailid();
        List<Donations> donations = service.getDonationsByEmail(email);
        model.addAttribute("donations", donations);
        return "viewDonarsDonations"; // Name of your Thymeleaf HTML file
    }

    return "redirect:/showLoginForm"; // Redirect to login if session is invalid
}


}
