package edu.practice.triangles.exception;

/**
 * Invalid Regex Format Exception
 */
public class InvalidRegexFormatException extends RuntimeException {

    public InvalidRegexFormatException() {
        this("НЕверный формат записи координат в таблице БД");
    }

    public InvalidRegexFormatException(String message) {
        super(message);
    }
}
