package hu.webuni.hr.lilla.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.FieldError;

public class MyError {
	
	private String message;
	private int errorCode;
	private List<FieldError> fieldErrors;

	

	public MyError(String message, int errorCode) {
		super();
		this.message = message;
		this.errorCode = errorCode;
		this.fieldErrors = new ArrayList<>();
	}

	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public int getErrorCode() {
		return errorCode;
	}
	
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}	
	
	public List<FieldError> getFieldErrors() {
		return fieldErrors;
	}
	
	public void addFieldError(FieldError fieldError) {
		fieldErrors.add(fieldError);
	}
	
	public void setFieldErrors(List<FieldError> fieldErrors) {
		this.fieldErrors = fieldErrors;
	}
}
