package com.operator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.EXPECTATION_FAILED)
public class PaymentNotValidException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	private String resourceName;
    private String fieldName; 
    private Object fieldValue; 

	public PaymentNotValidException( String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s is less than expected amount  %s : '%s'", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue; 
    }

    public String getResourceName() {
        return resourceName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public Object getFieldValue() {
        return fieldValue;
    }	

}
