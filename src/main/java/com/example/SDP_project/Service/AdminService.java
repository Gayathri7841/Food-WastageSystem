package com.example.SDP_project.Service;

import java.util.List;

import com.example.SDP_project.Model.Admin;
import com.example.SDP_project.Model.Donar;
import com.example.SDP_project.Model.Donations;
import com.example.SDP_project.Model.Recipient;

public interface AdminService {
Admin register(Admin admin);
Admin login(String email,String password);
List<Donar> getAllDonars();
List<Donations> getAllDonations();
List<Recipient> getAllRecipient();
int countTotalDonations();
int countAllDonars();
int countAllRecipients();
}
