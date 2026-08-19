package com.hotelreservation.exception;

public class DateOverlapException extends RuntimeException {
    public DateOverlapException(String message) {
        super(message);
    }

    public DateOverlapException(String message, Throwable cause) {
        super(message, cause);
    }
}