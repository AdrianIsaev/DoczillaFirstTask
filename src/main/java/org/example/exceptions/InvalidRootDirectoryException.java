package org.example.exceptions;

public class InvalidRootDirectoryException extends IllegalArgumentException{
    public InvalidRootDirectoryException(String message){
        super(message);
    }
}
