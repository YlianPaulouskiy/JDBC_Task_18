package edu.practice.triangles.logic;

import edu.practice.triangles.model.Triangle;

/**
 * Calculate param of triangle
 */
public interface TriangleLogic {

    /**
     * find out if a triangle if isosceles
     *
     * @param triangle Triangle element
     * @return true if isosceles
     */
    boolean isIsosceles(Triangle triangle);

    /**
     * find out if a triangle if rectangular
     *
     * @param triangle Triangle element
     * @return true if rectangular
     */
    boolean isRectangular(Triangle triangle);

    /**
     * find out if a triangle if obtuse
     *
     * @param triangle Triangle element
     * @return true if obtuse
     */
    boolean isObtuse(Triangle triangle);

    /**
     * find area of triangle
     *
     * @param triangle Triangle element
     * @return area value
     */
    double getArea(Triangle triangle);

}
