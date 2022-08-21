package edu.practice.triangles.util;


import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Do Simple operations with Strings
 */
public class StringUtil {

    /**
     * Remove extra symbol in line
     *
     * @param coordinateLine coordinate line in table of DB
     * @return line without extra symbols
     */
    public static String preparingCoordinate(String coordinateLine) {
        return coordinateLine.replace(" ", "").replace(")", "").replace("(", "");
    }

    /**
     * Check regex of coordinate line in table of DB
     *
     * @param coordinateLine coordinate line in table
     * @return boolean
     */
    public boolean isRightRegex(String coordinateLine) {
        Pattern coordinatePattern = Pattern.compile("^[(]\\d+[;|,]\\d+[)]$");
        Matcher matcher = coordinatePattern.matcher(coordinateLine);
        return matcher.matches();
    }

    /**
     * return coordinates of point
     *
     * @param coordinateLine coordinate line in table
     * @return massive coordinates
     */
    public int[] getCoordinate(String coordinateLine) {
        coordinateLine = preparingCoordinate(coordinateLine);
        return Arrays.stream(coordinateLine.split("[;|,]")).mapToInt(Integer::parseInt).toArray();
    }

}
