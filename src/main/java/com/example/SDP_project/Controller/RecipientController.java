package com.example.SDP_project.Controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

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

import com.example.SDP_project.Model.Donations;
import com.example.SDP_project.Model.Notification;
import com.example.SDP_project.Model.Recipient;
import com.example.SDP_project.Repository.NotificationRepository;
import com.example.SDP_project.Service.AdminService;
import com.example.SDP_project.Service.DonarService;
import com.example.SDP_project.Service.NotificationService;
import com.example.SDP_project.Service.RecipientService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class RecipientController {

    @Autowired
    private RecipientService service;
    @Autowired
    private AdminService service1;
    @Autowired
    private NotificationService service2;
    @Autowired
    private DonarService service3;
    @GetMapping("/showRecipientLoginForm")
    public ModelAndView LoginForm() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("RecipientLoginForm");
        return mv;
    }

    @PostMapping("/RecipientLoginVerification")
    public ModelAndView DonarLoginVerification(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        String email = request.getParameter("recipientemail");
        String password = request.getParameter("recipientpassword");

        // Verify login credentials
        Recipient recipient = service.RecipientLoginVerification(email, password);

        if (recipient != null) {
            // Store the Recipient object in the session
            HttpSession session = request.getSession();
            session.setAttribute("recipient", recipient);

            mv.setViewName("redirect:/showRecipientHome"); // Redirect to Recipient Home Page
        } else {
            mv.setViewName("Login");
            mv.addObject("msg", "Incorrect Credentials");
        }

        return mv;
    }

    @GetMapping("/showRecipientRegistration")
    public ModelAndView RecipientRegistrationForm() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("RecipientLoginForm");
        return mv;
    }

    @PostMapping("/RecipientRegistration")
    public ModelAndView RecipientRegistration(@ModelAttribute("recipient") Recipient recipient, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        List<Recipient> duplicateRecipients = service.VerifyDuplicates(recipient.getEmail(), recipient.getContactno());
        
        if (!duplicateRecipients.isEmpty()) {
            mv.setViewName("RecipientRegistrationForm");
            mv.addObject("duplicateerror", "Duplicate Account");
        } else {
            service.RecipientRegistration(recipient);
            mv.setViewName("redirect:/showRecipientLoginForm");
        }
        return mv;
    }
    @GetMapping("/showRecipientHome")
    public ModelAndView Recipient(HttpSession session) {
        ModelAndView mv = new ModelAndView();

        // Retrieve recipient from session
        Recipient recipient = (Recipient) session.getAttribute("recipient");

        if (recipient != null) {
            // Add recipient details to the model
            mv.addObject("recipient", recipient);
            mv.addObject("recipientId", recipient.getId());
            mv.addObject("name", recipient.getName());
            mv.addObject("email", recipient.getEmail());
            mv.addObject("contactno", recipient.getContactno());
            mv.addObject("message", recipient.getNotificationMessage());
            System.out.println("Name: " + recipient.getName());
            System.out.println("Email: " + recipient.getEmail());
            System.out.println("Contact No: " + recipient.getContactno());
            System.out.println("ID No: " + recipient.getId());
            System.out.println("Message: " + recipient.getNotificationMessage());

            // Fetch notifications for the recipient from the database
            List<Notification> notifications = service2.getNotificationsForRecipient(recipient.getId());

            if (notifications == null || notifications.isEmpty()) {
            	 
            	
                notifications = new ArrayList<>(); // Ensure no null issues
            }

            // Add notifications to the model
            mv.addObject("notifications", notifications);

            // Debug: Print notifications to console
            notifications.forEach(notification -> 
                System.out.println("Notification Message: " + notification.getMessage())
            );

            // Set the view name
            mv.setViewName("RecipientHome");
        } else {
            // Redirect to login page if recipient is not found in session
            mv.setViewName("redirect:/showRecipientLoginForm");
        }

        return mv;
    }

  

}

