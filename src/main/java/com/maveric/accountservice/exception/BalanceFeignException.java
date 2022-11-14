package com.maveric.accountservice.exception;

public class BalanceFeignException extends RuntimeException{
    public BalanceFeignException(String message){
        super(message);
    }
}
