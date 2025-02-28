package com.alves.waterfy.exception;

public class CharacterLimitException extends RuntimeException{

    public CharacterLimitException() {super ("Limite de caracteres excedido, forneca uma descrição e um titulo com tamanho maximo de ate 255 caracteres");}

    public CharacterLimitException(String message) {super(message);}
}
