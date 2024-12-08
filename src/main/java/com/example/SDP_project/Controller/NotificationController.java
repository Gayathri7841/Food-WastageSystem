package com.example.SDP_project.Controller;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.example.SDP_project.Model.Notification;
import com.example.SDP_project.Model.Recipient;
import com.example.SDP_project.Service.NotificationService;
import com.example.SDP_project.Service.RecipientService;

import jakarta.servlet.http.HttpSession;

@Controller
public class NotificationController {
@Autowired
private NotificationService service;
@Autowired
private RecipientService service1;

@GetMapping("/notifications")
public String getNotifications(HttpSession session, Model model, @RequestParam("recipientId") int recipientId) {
    Recipient sessionRecipient = (Recipient) session.getAttribute("recipient");

    if (sessionRecipient == null || sessionRecipient.getId() != recipientId) {
        return "redirect:/showRecipientLoginForm"; 
    }

    Recipient recipient = service1.findRecipientById(recipientId);
    if (recipient == null) {
        return "redirect:/errorPage"; // Handle invalid recipientId gracefully
    }

    model.addAttribute("recipient", recipient);
    List<Notification> notifications = service.getNotificationsForRecipient(recipient);
    model.addAttribute("notifications", notifications);

    return "NotificationsPage"; 
}

@GetMapping("/viewRecipientNotifications/{id}")
public String viewRecipientNotifications(@PathVariable int id, Model model) {
    Recipient recipient = service1.findRecipientById(id);
    if (recipient != null) {
        System.out.println("Recipient Notification: " + recipient.getNotificationMessage());
        model.addAttribute("recipient", recipient);
        return "NotificationsPage";
    }
    return "error";
}


}
