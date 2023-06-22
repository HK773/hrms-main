package com.example.demoproject.config;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demoproject.entitymodel.Employee;
import com.example.demoproject.utilities.PasswordUtil;

import jakarta.transaction.Transactional;

@Transactional
public class EmployeeInfoUserDetails implements UserDetails {
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private PasswordUtil passwordUtil;
	
	private String mailId;
	private String password;
	private List<GrantedAuthority> authorities;
	
	public EmployeeInfoUserDetails(Employee employee) {
	
		mailId=employee.getMailId();
		password=passwordUtil.passwordEncoder().encode(password);
		authorities=employee.getRoles().stream()
							.map(role-> new SimpleGrantedAuthority(role.getRoleName()))
							.collect(Collectors.toList());
	}
	
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//			
//		return new BCryptPasswordEncoder();
//	}
	
	@Override
	public List<? extends GrantedAuthority> getAuthorities() {
		
		return authorities;
	}

	@Override
	public String getPassword() {
		
		System.out.println("password: "+password);
		
		return password;
	}

	@Override
	public String getUsername() {
		System.out.println("mailId: "+ mailId);
		return mailId;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}


}

