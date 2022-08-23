package ru.trilonka.pastebox.util;

public class NotFoundEntityException extends RuntimeException {
    public NotFoundEntityException(String msg) {
        super(msg);
    }
}
