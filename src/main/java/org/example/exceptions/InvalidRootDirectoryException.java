package org.example.exceptions;

public final class InvalidRootDirectoryException extends IllegalArgumentException{
    public InvalidRootDirectoryException(String message){
        super(message);
    }
}
