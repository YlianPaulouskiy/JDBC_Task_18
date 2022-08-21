package edu.practice.triangles.converter;

import edu.practice.triangles.model.Point;
import edu.practice.triangles.util.StringUtil;

/**
 * Convert Point to String and vice versa
 */
public class Converter {

    /**
     * Point to String
     *
     * @param point Point value
     * @return String representation value
     */
    public String convert(Point point) {
        return "(" + point.getX() + ";" + point.getY() + ")";
    }

    /**
     * String to Point
     *
     * @param linePoint String representation
     * @return Point value
     */
    public Point convert(String linePoint) {
        StringUtil stringUtil = new StringUtil();
        int[] coordinate = stringUtil.getCoordinate(linePoint);
        return new Point(coordinate[0], coordinate[1]);
    }
}
