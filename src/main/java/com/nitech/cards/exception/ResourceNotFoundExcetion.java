package com.nitech.cards.exception;

public class ResourceNotFoundExcetion extends RuntimeException {
    public ResourceNotFoundExcetion(String resourceName, String fieldName, String fieldValue){
        super(String.format("%s not found with given input data %s : '%s'",resourceName,fieldName,fieldValue));
    }
}
