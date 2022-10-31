package com.maveric.accountservice.exception;

//customer id missmatch
public class CustomerIdMissmatch extends RuntimeException{
        public CustomerIdMissmatch(String message){super(message);
    }
}
