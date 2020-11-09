package com.notification.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST)
public class InvalidRequest extends RuntimeException {
    
    private static final long serialVersionUID = 1L;    
    private static final String INVALID_PARAM_MSG = "Invalid value [%s] found for paramater [%s].";
    private static final String INVALID_DATE_PARAM_MSG = "Invalid value [%s] found for paramater [%s]. Expected date format is [%s]";
    
    
    public InvalidRequest(String paramName, String value) {
        super(String.format(INVALID_PARAM_MSG, value, paramName));
    }
    
    public InvalidRequest(String paramName, String value, String format) {
        super(String.format(INVALID_DATE_PARAM_MSG, value, paramName, format));
    }
}
