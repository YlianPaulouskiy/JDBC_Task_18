package edu.practice.triangles.logic.impl;

import edu.practice.triangles.logic.TriangleLogic;
import edu.practice.triangles.model.Point;
import edu.practice.triangles.model.Triangle;

/**
 * Implement TriangleLogic
 */
public class TriangleLogicImpl implements TriangleLogic {


    @Override
    public boolean isIsosceles(Triangle triangle) {
        double[] sides = getSides(triangle);
        return sides[0] == sides[1] || sides[1] == sides[2] || sides[0] == sides[2];
    }

    @Override
    public boolean isRectangular(Triangle triangle) {
        return checkRectangular(getSides(triangle));
    }

    @Override
    public boolean isObtuse(Triangle triangle) {
        return checkObtuse(getSides(triangle));
    }

    @Override
    public double getArea(Triangle triangle) {
        double[] sides = getSides(triangle);
        double halfPerimeter = (sides[0] + sides[1] + sides[2]) / 2;
        return Math.sqrt(
                halfPerimeter
                        * (halfPerimeter - sides[0])
                        * (halfPerimeter - sides[1])
                        * (halfPerimeter - sides[2])
        );
    }

    //------------------------------------------------------------------------------------------------------------------

    /**
     * Calculates length between two points
     *
     * @param firstPoint  point
     * @param secondPoint point
     * @return length
     */
    private double getLength(Point firstPoint, Point secondPoint) {
        double sqrFirstSide = Math.pow(firstPoint.getX() - secondPoint.getX(), 2);
        double sqrSecondSide = Math.pow(firstPoint.getY() - secondPoint.getY(), 2);
        return Math.sqrt(sqrFirstSide + sqrSecondSide);
    }

    /**
     * Calculates length of all sides
     *
     * @param triangle Triangle element
     * @return massive sides length
     */
    private double[] getSides(Triangle triangle) {
        double[] sides = new double[3];
        sides[0] = getLength(triangle.getLeftPoint(), triangle.getMidPoint());
        sides[1] = getLength(triangle.getMidPoint(), triangle.getRightPoint());
        sides[2] = getLength(triangle.getRightPoint(), triangle.getLeftPoint());
        return sides;
    }

    /**
     * is rectangular by all sides
     *
     * @param sides sides of triangle
     * @return true if, rectangular
     */
    private boolean checkRectangular(double[] sides) {
        return sides[0] == Math.sqrt(sqr(sides[1]) + sqr(sides[2]))
                || sides[1] == Math.sqrt(sqr(sides[0]) + sqr(sides[2]))
                || sides[2] == Math.sqrt(sqr(sides[1]) + sqr(sides[0]));
    }

    /**
     * is obtuse by all sides
     *
     * @param sides sides of triangle
     * @return true if obtuse
     */
    private boolean checkObtuse(double[] sides) {
        return sides[0] > Math.sqrt(sqr(sides[1]) + sqr(sides[2]))
                || sides[1] > Math.sqrt(sqr(sides[0]) + sqr(sides[2]))
                || sides[2] > Math.sqrt(sqr(sides[1]) + sqr(sides[0]));
    }

    /**
     * Calculate square number
     *
     * @param side number
     * @return square of number
     */
    private double sqr(double side) {
        return Math.pow(side, 2);
    }
}
