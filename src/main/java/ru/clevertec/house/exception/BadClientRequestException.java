package ru.clevertec.house.exception;

public class BadClientRequestException extends RuntimeException {

    public BadClientRequestException(String message) {
        super(message);
    }

    public static BadClientRequestException of(Class<?> clazz, Object obj) {
        return new BadClientRequestException(clazz.getSimpleName() + "bad request (" + obj + ")");
    }
}
