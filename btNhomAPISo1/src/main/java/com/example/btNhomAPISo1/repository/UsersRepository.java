package com.example.btNhomAPISo1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.btNhomAPISo1.model.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer>{

	Users findByEmail(String email);

}
