package edu.practice.triangles.exception;

/**
 * Triangle Not Found Exception
 */
public class TriangleNotFoundException extends RuntimeException {

    public TriangleNotFoundException() {
        this("Треугольник с таким id не найден");
    }

    public TriangleNotFoundException(String message) {
        super(message);
    }
}
