package edu.practice.triangles.exception;

/**
 * Triangle Not Created Exception
 */
public class TriangleNotCreatedException extends RuntimeException{

    public TriangleNotCreatedException() {
        this("Треугольник не создан");
    }

    public TriangleNotCreatedException(String message) {
        super(message);
    }
}
