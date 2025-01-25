package com.example.btNhomAPISo1.requests;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RegisterRequest {
	private String userName;
	private String password;
	private String email;
}
