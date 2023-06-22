//package com.example.demoproject.entitymodel;
//
////@Entity
////@Table
//public class Token {
//	
////	@Id
////	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long id;
//	
//	private String token;
//	
////	@Enumerated(EnumType.STRING)
//	private TokenType tokenType;
//	
//	private boolean expired;
//	private boolean revoked;
//	
////	@ManyToOne
////	@JoinColumn(name="employee_id")
//	private Employee employee;
//	
//	
//	public Token(String token, TokenType tokenType, boolean expired, boolean revoked, Employee employee) {
//		super();
//		this.token = token;
//		this.tokenType = tokenType;
//		this.expired = expired;
//		this.revoked = revoked;
//		this.employee = employee;
//	}
//
//	public Token(Long id, String token, TokenType tokenType, boolean expired, boolean revoked, Employee employee) {
//		super();
//		this.id = id;
//		this.token = token;
//		this.tokenType = tokenType;
//		this.expired = expired;
//		this.revoked = revoked;
//		this.employee = employee;
//	}
//
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public String getToken() {
//		return token;
//	}
//
//	public void setToken(String token) {
//		this.token = token;
//	}
//
//	public TokenType getTokenType() {
//		return tokenType;
//	}
//
//	public void setTokenType(TokenType tokenType) {
//		this.tokenType = tokenType;
//	}
//
//	public boolean isExpired() {
//		return expired;
//	}
//
//	public void setExpired(boolean expired) {
//		this.expired = expired;
//	}
//
//	public boolean isRevoked() {
//		return revoked;
//	}
//
//	public void setRevoked(boolean revoked) {
//		this.revoked = revoked;
//	}
//
//	public Employee getEmployee() {
//		return employee;
//	}
//
//	public void setEmployee(Employee employee) {
//		this.employee = employee;
//	}
//
//	@Override
//	public String toString() {
//		return "Token [id=" + id + ", token=" + token + ", tokenType=" + tokenType + ", expired=" + expired
//				+ ", revoked=" + revoked + ", employee=" + employee + "]";
//	}
//	
//
//}
