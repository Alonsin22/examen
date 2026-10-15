package com.huertos.parcel_composite_service.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String resource, String id) {
        super(resource + " con id '" + id + "' no fue encontrada");
    }
}