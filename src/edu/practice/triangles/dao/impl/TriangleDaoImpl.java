package edu.practice.triangles.dao.impl;

import edu.practice.triangles.connection.ConnectionProvider;
import edu.practice.triangles.converter.Converter;
import edu.practice.triangles.dao.TriangleDao;
import edu.practice.triangles.exception.InvalidRegexFormatException;
import edu.practice.triangles.model.Point;
import edu.practice.triangles.model.Triangle;
import edu.practice.triangles.util.StringUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Implement TriangleDao
 */
public class TriangleDaoImpl implements TriangleDao {

    /**
     * Optional<Connect> to DB field
     */
    private final Optional<Connection> connection;

    /**
     * Constructor implement field connection
     */
    public TriangleDaoImpl() {
        connection = new ConnectionProvider().connection();
    }

    //------------------------------------------------------------------------------------------------------------------

    @Override
    public Optional<Triangle> findOne(Long id) {
        if (connection.isPresent()) {
            String query = "SELECT id, left_point, mid_point, right_point, date_creation, last_modified, version FROM all_triangles WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.get().prepareStatement(query)) {
                preparedStatement.setLong(1, id);
                return Optional.of(getResults(preparedStatement.executeQuery()).get(0));
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Triangle> findAll() {
        if (connection.isPresent()) {
            String query = "SELECT id, left_point, mid_point, right_point, date_creation, last_modified, version FROM all_triangles";
            try (Statement statement = connection.get().createStatement()) {
                return getResults(statement.executeQuery(query));
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
        return Collections.emptyList();
    }

    @Override
    public Optional<Triangle> create(Triangle source) {
        if (connection.isPresent()) {
            String query = "INSERT INTO all_triangles(left_point, mid_point, right_point) VALUES (?,?,?)";
            try (PreparedStatement preparedStatement = connection.get().prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, getPointString(source.getLeftPoint()));
                preparedStatement.setString(2, getPointString(source.getMidPoint()));
                preparedStatement.setString(3, getPointString(source.getRightPoint()));
                preparedStatement.executeUpdate();
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    return findOne(resultSet.getLong(1));
                }
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<Triangle> update(Triangle source) {
        if (connection.isPresent()) {
            Optional<Triangle> triangle = findOne(source.getId());
            if (triangle.isPresent()) {
                String query = "UPDATE all_triangles SET left_point = ?, mid_point = ?, right_point = ?, last_modified = NOW(), version = (SELECT  version FROM all_triangles WHERE id = ?) WHERE id = ?";
                try (PreparedStatement preparedStatement = connection.get().prepareStatement(query)) {
                    preparedStatement.setString(1, getPointString(source.getLeftPoint()));
                    preparedStatement.setString(2, getPointString(source.getMidPoint()));
                    preparedStatement.setString(3, getPointString(source.getRightPoint()));
                    preparedStatement.setLong(4, source.getId());
                    preparedStatement.setLong(5, source.getId());
                    preparedStatement.executeUpdate();
                    return findOne(source.getId());
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public void remove(Long id) {
        if (connection.isPresent()) {
            String query = "DELETE FROM all_triangles WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.get().prepareStatement(query)) {
                preparedStatement.setLong(1, id);
                preparedStatement.executeUpdate();
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
    }

    //------------------------------------------------------------------------------------------------------------------

    /**
     * Get all results from in table by ResultSet
     *
     * @param resultSet result set
     * @return List results
     */
    private List<Triangle> getResults(ResultSet resultSet) {
        try {

            List<Triangle> triangleList = new ArrayList<>();
            while (resultSet.next()) {
                Triangle triangle = new Triangle();
                triangle.setId(resultSet.getLong("id"));
                triangle.setLeftPoint(getPoint(resultSet.getString("left_point")));
                triangle.setMidPoint(getPoint(resultSet.getString("mid_point")));
                triangle.setRightPoint(getPoint(resultSet.getString("right_point")));
                triangle.setDateCreation(resultSet.getDate("date_creation"));
                triangle.setLastModified(resultSet.getDate("last_modified"));
                triangle.setVersion(resultSet.getLong("version"));
                triangleList.add(triangle);
            }
            return triangleList;
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return Collections.emptyList();
    }

    /**
     * Convert Line to Point
     *
     * @param line String element
     * @return Point element
     */
    private Point getPoint(String line) {
        Converter converter = new Converter();
        StringUtil stringUtil = new StringUtil();
        if (stringUtil.isRightRegex(line)) {
            return converter.convert(line);
        } else {
            throw new InvalidRegexFormatException();
        }
    }

    /**
     * Convert Point to String
     *
     * @param point Point element
     * @return String element
     */
    private String getPointString(Point point) {
        Converter converter = new Converter();
        return converter.convert(point);
    }
}
