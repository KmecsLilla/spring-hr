package hu.webuni.hr.lilla.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import hu.webuni.hr.lilla.service.SalaryException;
import hu.webuni.hr.lilla.service.StartToWorkException;

@RestControllerAdvice
public class CustomExceptionHandler {
	
	private static final Logger log = LoggerFactory.getLogger(CustomExceptionHandler.class);
		
	@ExceptionHandler(StartToWorkException.class)
	public ResponseEntity<MyError> handleStartToWorkException(StartToWorkException e, WebRequest req) {
		log.warn(e.getMessage(), e);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MyError(e.getMessage(), 1122));
	}
	
	@ExceptionHandler(SalaryException.class)
	public ResponseEntity<MyError> handleSalaryException(SalaryException e, WebRequest req) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MyError(e.getMessage(), 1222));
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<MyError> handleValidationError(MethodArgumentNotValidException e, WebRequest req) {
		MyError myError = new MyError(e.getMessage(), 1522);
		myError.setFieldErrors(e.getBindingResult().getFieldErrors());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(myError);
	}
}
	
//EZ így nem jó sajna, javítandó!	
//	@ExceptionHandler(StartToWorkException.class)
//	public ResponseEntity<MyError> handleStartToWorkException(StartToWorkException e, WebRequest req) {
//		MyError myError = new MyError(e.getMessage(), 1122);
//		myError.setFieldErrors(e.getBindingResult().getFieldErrors());
//		log.warn(e.getMessage(), e);
//		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(myError);
//	}
//	
//	@ExceptionHandler(SalaryException.class)
//	public ResponseEntity<MyError> handleSalaryException(SalaryException e, WebRequest req) {
//		MyError myError = new MyError(e.getMessage(), 1222);
//		myError.setFieldErrors(e.getBindingResult().getFieldErrors());
//		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(myError);
//	}
//	
//	@ExceptionHandler(MethodArgumentNotValidException.class)
//	public ResponseEntity<MyError> handleValidationError(MethodArgumentNotValidException e, WebRequest req) {
//		MyError myError = new MyError(e.getMessage(), 1522);
//		myError.setFieldErrors(e.getBindingResult().getFieldErrors());
//		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(myError);
//	}
//}
	
