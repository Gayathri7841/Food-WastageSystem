package com.example.SDP_project.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator {
	 public static void main(String[] args) {
	        String password = "Gayathri@123";
	        String regex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";

	        Pattern pattern = Pattern.compile(regex);
	        Matcher matcher = pattern.matcher(password);

	        if (matcher.matches()) {
	            System.out.println("Password is strong.");
	        } else {
	            System.out.println("Password is weak.");
	        }
	    }
}
