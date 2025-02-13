package org.example.exceptions;

public final class CyclicDependencyException extends Exception{
    public CyclicDependencyException(String message){
        super(message);
    }
}
