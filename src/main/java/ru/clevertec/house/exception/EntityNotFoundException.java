package ru.clevertec.house.exception;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String message) {
        super(message);
    }

    public static EntityNotFoundException of(Class<?> clazz, Object obj) {
        return new EntityNotFoundException("Not fount " + clazz.getSimpleName() + "with value " + obj);
    }
}
