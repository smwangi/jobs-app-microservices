package org.samwan.admin.exceptions;


public class LocationNotFoundException extends RuntimeException {

    public LocationNotFoundException(Long id){
        super("Could not find Location "+id);
    }
}
