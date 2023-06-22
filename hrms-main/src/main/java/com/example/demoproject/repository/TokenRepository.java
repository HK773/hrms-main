//package com.example.demoproject.repository;
//
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//
//import com.example.demoproject.entitymodel.Token;
//
////@Repository
//public interface TokenRepository extends JpaRepository<Token, Long> {
//	
//	
//	@Query("select t from Token t INNER JOIN Employee e on t.employee.id= e.id"
//			+ "where e.id=:reqId AND (t.expired=false OR t.revoked=false)")
//	List<Token> findAllValidTokenByEmployee(@Param("reqId") Long reqId);
//	
//	Optional<Token> findByToken(String reqToken);
//	
//}
