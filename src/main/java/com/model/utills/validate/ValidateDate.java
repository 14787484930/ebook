package com.model.utills.validate;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ValidateDate {

    public static Map checkDate(BindingResult result){

        Map<String,Object> map = new HashMap<String,Object>();

        List<FieldError> errors = result.getFieldErrors();
        for(FieldError error:errors){
            System.out.println(error.getField()+" "+error.getDefaultMessage());
            map.put(error.getField(),error.getDefaultMessage());
        }
        return map;
    }
}
