package com.socialhoaxify.wsfs.error;

import lombok.AllArgsConstructor;
import org.hibernate.type.TrueFalseType;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
public class ErrorHandlerForSpringSecur implements ErrorController {


    private final ErrorAttributes errorAttributes;


//    ErrorAttributeOptions.defaults()

    @RequestMapping("/error")
     ApiError handleError(WebRequest webRequest) {
        Map<String,Object> attributes=this.errorAttributes.getErrorAttributes(webRequest,ErrorAttributeOptions.of(ErrorAttributeOptions.Include.MESSAGE, ErrorAttributeOptions.Include.BINDING_ERRORS));
        String message=(String)attributes.get("message");
        String path=(String)attributes.get("path");
        int status=(Integer)attributes.get("status");
        ApiError error=new ApiError(status,message,path);
        if(attributes.containsKey("errors")){
        List<FieldError> fieldErrorList=(List<FieldError>) attributes.get("errors");
        Map<String,String> validationErrors=new HashMap<>();
        for(FieldError fieldError:fieldErrorList){
            validationErrors.put(fieldError.getField(),fieldError.getDefaultMessage());
        }
            error.setValidationErrors(validationErrors);
        }

        return error;

    }



}