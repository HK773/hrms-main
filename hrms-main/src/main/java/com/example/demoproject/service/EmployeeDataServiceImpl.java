package com.example.demoproject.service;


import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demoproject.config.SecurityConfiguration;
import com.example.demoproject.dto.EmployeeDetailsDTO;
import com.example.demoproject.dto.EmployeeLoginDetailsDTO;
import com.example.demoproject.entitymodel.Employee;
import com.example.demoproject.entitymodel.Role;
import com.example.demoproject.repository.EmployeeRepository;
import com.example.demoproject.repository.RoleRepository;
import com.example.demoproject.utilities.LinkGeneration;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.transaction.Transactional;


@Service
@Transactional
public class EmployeeDataServiceImpl implements EmployeeDataService{
	
	@Autowired
	private RoleDataService roleDataService;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	@Qualifier("employeeRepository")
	private EmployeeRepository employeeRepository;
	
	@Autowired
	@Qualifier("roleRepository")
	private RoleRepository roleRepository;
		
	@Autowired
	private SecurityConfiguration securityConfiguration;
	

	@Autowired
	private LinkGeneration linkgeneration;
	
//	@Autowired
//	private EmployeeLoginDetailsDTO employeeLoginDTO;
	
	@Value("${server.address}")
	private String serverAddress;
	
	@Value("${server.servlet.context-path}")	
	private String path; // Base URL of your API
	
	@Value("${server.port}")
	private int serverPort;
	
	public EmployeeDataServiceImpl(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}
	
	@Override
	public boolean existsById(Long id) {
		return employeeRepository.existsById(id);
	}

	@Override
	public boolean existsByFirstName(String firstName) {
		
		return employeeRepository.existsByFirstName(firstName);
	}

	@Override
	public boolean existsByMailId(String mailId) {
		
		return employeeRepository.existsByMailId(mailId);
	}

	
	@Override
	public Employee saveEmployeeService(Employee reqEmployee) {
		
		if (reqEmployee.getContactNumber()
				.equals(reqEmployee.getEmergencyContactNumber()) &&
			reqEmployee.getMailId().equalsIgnoreCase(reqEmployee.getPersonalMailId())) {
			
			return null;
			
		} else {
			
		String password = generateRandomPassword(reqEmployee.getMailId());
		
		Employee empValue = new Employee();
		empValue.setEmployeeCode(reqEmployee.getEmployeeCode());
		empValue.setFirstName(reqEmployee.getFirstName()); 
		empValue.setContactNumber(reqEmployee.getContactNumber());
		empValue.setMailId(reqEmployee.getMailId());
		empValue.setPassword(password);
		empValue.getRoles().addAll(
			reqEmployee.getRoles().stream().map(
					role->{
						Role rr = roleDataService
								.fetchRoleById(role.getId());
						rr.getEmployee().add(empValue);
						return rr;
					}).collect(Collectors.toList()));
		empValue.setEmployeeTypes(reqEmployee.getEmployeeTypes());
		empValue.setDepartment(	reqEmployee.getDepartment());
		empValue.setDesignation(reqEmployee.getDesignation());
		empValue.setMartialStatus(reqEmployee.getMartialStatus()); 
		empValue.setGender(reqEmployee.getGender());
		empValue.setBankAccountType(reqEmployee.getBankAccountType());
		empValue.setBankAccountNumber(reqEmployee.getBankAccountNumber());
		empValue.setUniversityName(reqEmployee.getUniversityName());
		empValue.setHighestQualification(reqEmployee.getHighestQualification());
		empValue.setYearOfPassing(reqEmployee.getYearOfPassing());
		empValue.setDateOfBirth(reqEmployee.getDateOfBirth());
		empValue.setFatherName(reqEmployee.getFatherName());
		empValue.setMotherName(reqEmployee.getMotherName());
		empValue.setAge(reqEmployee.getAge());
		empValue.setBloodGroup(reqEmployee.getBloodGroup());
		empValue.setPersonalMailId(reqEmployee.getPersonalMailId());
		empValue.setEsiNumber(reqEmployee.getEsiNumber());
		empValue.setPfNumber(reqEmployee.getPfNumber());
		empValue.setUanNumber(reqEmployee.getUanNumber()); 
		empValue.setPanNumber(reqEmployee.getPanNumber());
		empValue.setShifts(reqEmployee.getShifts());
		empValue.setAadharCardNumber(reqEmployee.getAadharCardNumber()); 
		empValue.setPermanentAddress(reqEmployee.getPermanentAddress());
		empValue.setPostalAddress(reqEmployee.getPostalAddress());
		empValue.setEmergencyContactNumber(reqEmployee.getEmergencyContactNumber());
		empValue.setEmergencyContactName(reqEmployee.getEmergencyContactName());
		empValue.setEmergencyContactRelation(reqEmployee.getEmergencyContactRelation());
		empValue.setStatus(reqEmployee.getStatus());
		
		employeeRepository.save(empValue);
			
		return empValue;
		}
	}
	
	@Override
	public void sendMail(String  mailId)  {
		
		String senderMailId = "honnika.k@dollarbirdinc.com";
		String subject = "Registration Mail";
		String password = generateRandomPassword(mailId);
		MimeMessage message = mailSender.createMimeMessage();
		
		try {
			
			message.setFrom(senderMailId);
			message.setRecipients(MimeMessage.RecipientType.TO, mailId);
			message.setSubject(subject);
			
			String htmlContent = "<h1> Welcome To HRMS Demo</h1>"+ "\r\n"
								+ "<p>Email: " + mailId+ "</p>"+ "\r\n"
								+ "<p>Password: " + password + "</p>"+ "\r\n"
								+ "<a href=" + linkgeneration.generateLink("/login") + "> Click here to Login" +"</a>";
			
			message.setContent(htmlContent,"text/html; charset=utf-8");
			
			mailSender.send(message);
			
			System.out.println("Mail Sent Successfully");
			
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	
		
	}
		
	@Override
	public String generateRandomPassword(String mailId) {
		
        final String chars = mailId;
        
        String specialCharacters = "!@#$";
        String numbers = "1234567890";
        String combinedChars = chars.toLowerCase() + specialCharacters + numbers;
        Random random = new Random();
        char[] password = new char[9];
        
        for(int i = 4; i< 9 ; i++) {
            password[i] = combinedChars.charAt(random.nextInt(combinedChars.length()));
         }
        return password.toString();
		 	
	}
	
	@Override
	public String updateEmployeePasswordService(
			String username, String password) {
		
		if (username.isEmpty() && password.isEmpty()) {
			throw new NoSuchElementException("Invalid Username and Password");
		} else {
			
			Employee employee = employeeRepository.findByMailId(username).get();
			
			employee.setPassword(securityConfiguration
					.passwordEncoder()
					.encode(password));
			
			employeeRepository.save(employee);
			
			return "Successfully Re-set password";
		}
		
		
	
	}

	@Override
	public List<Employee> retrieveAllEmployeeDetailsService() {
		List<Employee> listOfEmployee = employeeRepository.findAll();
		return listOfEmployee;
		
	}
		
	@Override
	public Employee retrieveEmployeeDetailsByIdService(Long id) {
		
		if (employeeRepository.existsById(id)==false) {
			return null;
		} else {
			Employee empValue = employeeRepository.findById(id).get();
			return empValue;
		}
	}
	
	@Override
	public Employee updateEmployeeDetailsService(Employee reqEmployee, 
			Long employeeId) {
		
		if (Objects.isNull(reqEmployee) || employeeId==0) {	
			return null;
		}
	
		if (employeeRepository.existsByMailId(reqEmployee.getMailId())==true &&
				employeeRepository.existsById(employeeId)==false) {
			return null;
		} else {
		
		Employee empValue = retrieveEmployeeDetailsByIdService(employeeId);
		
		empValue.setEmployeeCode(reqEmployee.getEmployeeCode());
		empValue.setFirstName(reqEmployee.getFirstName()); 
		empValue.setContactNumber(reqEmployee.getContactNumber());
		empValue.setMailId(reqEmployee.getMailId()); 
		empValue.setRoles(
				reqEmployee.getRoles().stream().map(
						role-> {
							Role roleFromRepo = roleDataService.fetchRoleById(role.getId());
							roleFromRepo.getEmployee().add(empValue);
							return roleFromRepo;
						}).collect(Collectors.toList())
					);
		empValue.setEmployeeTypes(reqEmployee.getEmployeeTypes());
		empValue.setDepartment(	reqEmployee.getDepartment());
		empValue.setDesignation(reqEmployee.getDesignation());
		empValue.setMartialStatus(reqEmployee.getMartialStatus()); 
		empValue.setGender(reqEmployee.getGender());
		empValue.setShifts(reqEmployee.getShifts());
		empValue.setBankAccountType(reqEmployee.getBankAccountType());
		empValue.setBankAccountNumber(reqEmployee.getBankAccountNumber());
		empValue.setUniversityName(reqEmployee.getUniversityName());
		empValue.setHighestQualification(reqEmployee.getHighestQualification());
		empValue.setYearOfPassing(reqEmployee.getYearOfPassing());
		empValue.setDateOfBirth(reqEmployee.getDateOfBirth());
		empValue.setFatherName(reqEmployee.getFatherName());
		empValue.setMotherName(reqEmployee.getMotherName());
		empValue.setAge(reqEmployee.getAge());
		empValue.setBloodGroup(reqEmployee.getBloodGroup());
		empValue.setPersonalMailId(reqEmployee.getPersonalMailId());
		empValue.setEsiNumber(reqEmployee.getEsiNumber());
		empValue.setPfNumber(reqEmployee.getPfNumber());
		empValue.setUanNumber(reqEmployee.getUanNumber()); 
		empValue.setPanNumber(reqEmployee.getPanNumber());
		empValue.setAadharCardNumber(reqEmployee.getAadharCardNumber()); 
		empValue.setPermanentAddress(reqEmployee.getPermanentAddress());
		empValue.setPostalAddress(reqEmployee.getPostalAddress());
		empValue.setEmergencyContactNumber(reqEmployee.getEmergencyContactNumber());
		empValue.setEmergencyContactName(reqEmployee.getEmergencyContactName());
		empValue.setEmergencyContactRelation(reqEmployee.getEmergencyContactRelation());
		empValue.setStatus(reqEmployee.getStatus());

		employeeRepository.save(empValue);
		
	
		
		return empValue; 
		
		}
	}

	
	@Override
	public List<EmployeeDetailsDTO> fetchEmployeeListService() {
		List<EmployeeDetailsDTO> empList = employeeRepository
				.retriveEmployeeList();
		return empList;
	}

	@Override
	public boolean deleteEmployeeDetailService(Long id) {
		
		if (employeeRepository.existsById(id)==false) {
			return false;
		} else {
			
		 Employee employeeData = employeeRepository.findById(id).get();
		 
		 
		 //Soft delete
		 employeeData.setStatus("Inactive");
		 employeeRepository.save(employeeData);
		 
		 //Delete 
		 employeeRepository.deleteById(id);
		 
		 return true;
		 }
		
	}

	@Override
	public EmployeeLoginDetailsDTO fetchLoginDetails(String mailId) {
		
		Employee empVal = employeeRepository.findByMailId(mailId).get();
		String password=null;
		
		if (existsByMailId(mailId) == false) {
			throw new UsernameNotFoundException("Invalid Mail Id");
		} 
		
		
		if (empVal.getMailId()==mailId) {
			
			System.out.println("Repo: " + empVal.getMailId());
			System.out.println("req: " + mailId);
			password = empVal.getPassword();
			return new EmployeeLoginDetailsDTO(mailId,password);
		} else {
			return null;
		}
		
	}

//	@Override
//	public Boolean employeeLoginService(EmployeeLoginDetailsDTO employeeLoginDto) {
//		
//		String mailId = employeeLoginDto.getMailId();
//		
//		Employee employee = employeeRepository.findByMailId(mailId).get();
//		
//		if (employee.getMailId().equalsIgnoreCase(mailId) 
//				&& employee.getPassword().equals(employeeLoginDto.getPassword())) {
//			
//			return true;
//			
//		} else {
//			return false;
//		}
//		
//	}

	

	
	

	
}

