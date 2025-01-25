package com.example.btNhomAPISo1.service;

import com.example.btNhomAPISo1.model.Users;
import com.example.btNhomAPISo1.requests.RegisterRequest;
import com.example.btNhomAPISo1.responses.RegisterResponse;

public interface UserService {
	RegisterResponse register(RegisterRequest registerRequest);
	
	void verify(String email, String otp);
	
	Users login(String email, String password);
	
	void sendResetPasswordOtp(String email);
	
	void resetPassword(String email, String newPassword, String otp);
}
