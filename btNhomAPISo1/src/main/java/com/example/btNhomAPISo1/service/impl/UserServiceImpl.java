package com.example.btNhomAPISo1.service.impl;

import java.util.Random;

import javax.management.RuntimeErrorException;

import org.springframework.stereotype.Service;

import com.example.btNhomAPISo1.model.Users;
import com.example.btNhomAPISo1.repository.UsersRepository;
import com.example.btNhomAPISo1.requests.RegisterRequest;
import com.example.btNhomAPISo1.responses.RegisterResponse;
import com.example.btNhomAPISo1.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
	private final UsersRepository usersRepository;
	private final EmailService emailService;
	@Override
	public RegisterResponse register(RegisterRequest registerRequest) {
		Users existingUser = usersRepository.findByEmail(registerRequest.getEmail());
		if (existingUser != null && existingUser.isVerified()) {
			throw new RuntimeException("Tai khoan da duoc dang ky");
		}
		Users users = Users.builder()
				.user_name(registerRequest.getUserName())
				.email(registerRequest.getEmail())
				.password(registerRequest.getPassword())
				.build();
		
		String otp = generateOTP();
		users.setOtp(otp); 
		sendVerificationEmail(users.getEmail(), otp);
		
		usersRepository.save(users);
		
		RegisterResponse response = RegisterResponse.builder()
				.userName(users.getUser_name())
				.email(users.getEmail())
				.build();
		return response;
		
	}
	
	@Override
	public Users login(String email, String password) {
		Users byMail = usersRepository.findByEmail(email);
		if (byMail != null && byMail.isVerified() && byMail.getPassword().equals(password)) {
			return byMail;
		}
		
		else {
			throw new RuntimeException("Internal server error");
		}
	}
	
	@Override
	public void sendResetPasswordOtp(String email) {
		String otp = generateOTP();
		Users resetUser = usersRepository.findByEmail(email);
		if (resetUser == null) {
			throw new RuntimeException("Khong tim thay nguoi dung voi email da nhap");
		} 
		
		else {
			resetUser.setOtp(otp);
			usersRepository.save(resetUser);
			String subject = "Xac thuc email cua ban de doi mat khau";
			String body = "Ma xac thuc cua ban la: " + otp; 
			emailService.sendEmail(email, subject, body);
		}
	}
	
	@Override
	public void resetPassword(String email, String newPassword, String otp) {
		Users resetUser = usersRepository.findByEmail(email);
		if (resetUser == null) {
			throw new RuntimeException("Khong tim thay nguoi dung"); //Khong the xay ra neu nhu chuyen huong tu /forgot, nhung van lam de kiem tra
		}
		else if (otp.equals(resetUser.getOtp())) { 
			resetUser.setPassword(newPassword);
			usersRepository.save(resetUser);
		}
		else {
			throw new RuntimeException("Internal error");
		}
		
		
	}	
	
	private String generateOTP() {
		Random random = new Random();
		int otpVal = 100000 + random.nextInt(900000); //Dam bao otp luon du 6 chu so
		return String.valueOf(otpVal);
	}
	
	private void sendVerificationEmail(String email, String otp) {
		String subject = "Xac thuc email cua ban";
		String body = "Ma xac thuc cua ban la: " + otp; 
		emailService.sendEmail(email, subject, body);
	}

	@Override
	public void verify(String email, String otp) {
		Users users = usersRepository.findByEmail(email);
		if (users == null) {
			throw new RuntimeException("Khong tim thay nguoi dung");
		}
		else if (users.isVerified()) {
			throw new RuntimeException("Nguoi dung da xac thuc");
		}
		else if (otp.equals(users.getOtp())) { 
			users.setVerified(true);
			usersRepository.save(users);
		}
		else {
			throw new RuntimeException("Internal error");
		}
	}

	

	

	
}
