package edu.practice.triangles.dao.impl;

import edu.practice.triangles.connection.ConnectionProvider;
import edu.practice.triangles.dao.OtherTriangleDao;
import edu.practice.triangles.dao.TriangleDao;
import edu.practice.triangles.exception.TriangleNotCreatedException;
import edu.practice.triangles.model.Triangle;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class OtherTriangleDaoImpl implements OtherTriangleDao {

    private final Optional<Connection> connection;
    private final TriangleDao triangleDao;

    public OtherTriangleDaoImpl() {
        connection = new ConnectionProvider().connection();
        triangleDao = new TriangleDaoImpl();
    }

    //------------------------------------------------------------------------------------------------------------------

    @Override
    public Optional<Triangle> findOne(String table, Long id) {
        if (connection.isPresent()) {
            String query = "SELECT * FROM " + table + " WHERE id = ?";
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
    public List<Triangle> findAll(String table) {
        if (connection.isPresent()) {
            String query = "SELECT * FROM " + table;
            try (Statement statement = connection.get().createStatement()) {
                return getResults(statement.executeQuery(query));
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
        return Collections.emptyList();
    }

    @Override
    public Optional<Triangle> create(String table, Triangle source) {
        if (connection.isPresent()) {
            String query = "INSERT INTO " + table + "(triangle_id) VALUES (?)";
            try (PreparedStatement preparedStatement = connection.get().prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setLong(1, source.getId());
                preparedStatement.executeUpdate();
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    return findOne(table, resultSet.getLong(1));
                }
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
        return Optional.empty();
    }

    @Override
    public void remove(String table, Long id) {
        if (connection.isPresent()) {
            String query = "DELETE FROM " + table + " WHERE triangle_id = ?";
            try (PreparedStatement preparedStatement = connection.get().prepareStatement(query)) {
                preparedStatement.setLong(1, id);
                preparedStatement.executeUpdate();
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
    }

    //------------------------------------------------------------------------------------------------------------------

    private List<Triangle> getResults(ResultSet resultSet) {
        try {
            List<Triangle> triangleList = triangleDao.findAll();
            List<Triangle> resultList = new ArrayList<>();
            while (resultSet.next()) {
                Long id = resultSet.getLong(2);
                for (Triangle triangle : triangleList) {
                    if (triangle.getId().equals(id)) {
                        resultList.add(triangle);
                    }
                }
            }
            return resultList;
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return Collections.emptyList();
    }
}
