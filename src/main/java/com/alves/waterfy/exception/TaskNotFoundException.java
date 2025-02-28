package com.alves.waterfy.exception;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(){super("Tarefa nao encontrada com o id informado.");}

    public TaskNotFoundException(String message) {super(message);}

}
