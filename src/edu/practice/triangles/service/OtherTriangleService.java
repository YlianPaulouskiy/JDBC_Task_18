package edu.practice.triangles.service;

import edu.practice.triangles.model.Triangle;

import java.util.List;

/**
 * Queries any table from DB
 */
public interface OtherTriangleService {

    /**
     * find one field from DB by id
     *
     * @param table table name
     * @param id    id in table DB
     * @return Triangle element
     */
    Triangle findOne(String table, Long id);

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
     * @return Triangle
     */
    Triangle save(String table, Triangle source);

    /**
     * Delete field from table in DB by id
     *
     * @param table table name
     * @param id    id in table
     */
    void remove(String table, Long id);
}
