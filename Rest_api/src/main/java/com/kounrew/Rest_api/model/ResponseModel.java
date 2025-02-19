package com.kounrew.Rest_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseModel {
	private boolean status;
	
	private String message;
	
	private Object body;
	
}
