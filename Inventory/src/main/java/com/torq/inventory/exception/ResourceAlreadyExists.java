package com.torq.inventory.exception;

public class ResourceAlreadyExists extends RuntimeException{
    private String resourceName;
    private String fieldName;
    private String fieldValue;

    public ResourceAlreadyExists(String resourceName , String fieldName , String fieldValue){
        super(String.format("%s already exists for %s : %s",resourceName , fieldName , fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
