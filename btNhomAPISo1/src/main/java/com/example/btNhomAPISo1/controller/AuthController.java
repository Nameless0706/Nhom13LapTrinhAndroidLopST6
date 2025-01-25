package com.example.btNhomAPISo1.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.btNhomAPISo1.model.Users;
import com.example.btNhomAPISo1.requests.RegisterRequest;
import com.example.btNhomAPISo1.responses.RegisterResponse;
import com.example.btNhomAPISo1.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
	
	//Xu ly du lieu duoc truyen qua cac phuong thuc
	
	private final UserService userService;
	
	@PostMapping("/register") 
	public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest registerRequest){
		RegisterResponse registerResponse = userService.register(registerRequest);
		return new ResponseEntity<>(registerResponse, HttpStatus.CREATED);
	}
	
	@PostMapping("/verify")  
	public ResponseEntity<?> verifyUser(@RequestParam String email, @RequestParam String otp){
		try {
			userService.verify(email, otp);
			return new ResponseEntity<>("Xac thuc thanh cong", HttpStatus.OK);
		}catch (RuntimeException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password){
		Users users = userService.login(email, password);
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
	
	@PostMapping("/forgot")
	public ResponseEntity<?> sendResetPasswordOtp(@RequestParam String email){
		try {
			userService.sendResetPasswordOtp(email);
			return new ResponseEntity<>("Da gui ma OTP ve mail cua ban, vui long kiem tra", HttpStatus.OK);
		}catch (RuntimeException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/reset-password")
	public ResponseEntity<?> resetPassword(@RequestParam String email, @RequestParam String newPassword, @RequestParam String otp){
		try {
			userService.resetPassword(email, newPassword, otp);
			return new ResponseEntity<>("Thay doi mat khau thanh cong", HttpStatus.OK);
		}catch (RuntimeException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

}
