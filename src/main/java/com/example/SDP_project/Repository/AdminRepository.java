package com.example.SDP_project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.SDP_project.Model.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

   Admin findByEmailAndPassword(String email,String password);
}
