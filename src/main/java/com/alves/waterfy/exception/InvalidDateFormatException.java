package com.alves.waterfy.exception;

public class InvalidDateFormatException extends RuntimeException{
    public InvalidDateFormatException(){super("A data recebida nao segue os padroes 'YYYY-MM-DD'.");}

    public InvalidDateFormatException(String message) {super(message);}
}
