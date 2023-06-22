package com.example.demoproject.utilities;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordUtil {
	
	
	public BCryptPasswordEncoder passwordEncoder() {
			
		return new BCryptPasswordEncoder();
	}
	
	public boolean matches(String rawPassword, String encodedPassword) {
		
		if (passwordEncoder().encode(rawPassword).equals(encodedPassword)) {
			
			return true;
		} 
		return false;
		
	}
}
