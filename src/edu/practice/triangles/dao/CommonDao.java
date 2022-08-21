package edu.practice.triangles.dao;

import java.util.List;
import java.util.Optional;

/**
 * Common Dao of simple requests to database
 *
 * @param <T> param of class
 */
public interface CommonDao<T> {

    /**
     * find one field from DB by id
     *
     * @param id id in table DB
     * @return Optional <T>
     */
    Optional<T> findOne(Long id);

    /**
     * find all fields <T> from DB
     *
     * @return List<T>
     */
    List<T> findAll();

    /**
     * Create new field in table DB if there is not exist
     *
     * @param source <T>
     * @return Optional of <T>
     */
    Optional<T> create(T source);

    /**
     * Update field in table DB if there already exist
     *
     * @param source <T>
     * @return Optional of <T>
     */
    Optional<T> update(T source);

    /**
     * Delete field from table in DB by id
     *
     * @param id id in table
     */
    void remove(Long id);

}
