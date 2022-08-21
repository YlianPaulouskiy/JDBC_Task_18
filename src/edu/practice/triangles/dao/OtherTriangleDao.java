package edu.practice.triangles.dao;

import edu.practice.triangles.model.Triangle;

import java.util.List;
import java.util.Optional;

/**
 * Queries any table from DB
 */
public interface OtherTriangleDao {

    /**
     * find one field from DB by id
     *
     * @param table table name
     * @param id    id in table DB
     * @return Optional Triangle
     */
    Optional<Triangle> findOne(String table, Long id);

    /**
     * find all fields Triangles in table from DB
     *
     * @param table table name
     * @return List<Triangles>
     */
    List<Triangle> findAll(String table);

    /**
     * Create new field in table DB if there is not exist
     *
     * @param table  table name
     * @param source Triangle element
     * @return Optional Triangle
     */
    Optional<Triangle> create(String table, Triangle source);

    /**
     * Delete field from table in DB by id
     *
     * @param table table name
     * @param id    id in table
     */
    void remove(String table, Long id);
}
