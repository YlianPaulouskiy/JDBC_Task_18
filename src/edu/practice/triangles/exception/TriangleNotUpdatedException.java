package edu.practice.triangles.exception;

/**
 * Triangle Not Updated Exception
 */
public class TriangleNotUpdatedException extends RuntimeException {

    public TriangleNotUpdatedException() {
        this("Треугольник с таким id не был обновлен");
    }

    public TriangleNotUpdatedException(String message) {
        super(message);
    }
}
