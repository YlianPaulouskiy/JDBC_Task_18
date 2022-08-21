package edu.practice.triangles.service;

import java.util.List;

/**
 * Get simple requests to database
 *
 * @param <T> param what you need
 */
public interface CommonService<T> {

    /**
     * find one field from DB by id
     *
     * @param id id in table DB
     * @return T element
     */
    T findOne(Long id);

    /**
     * find all fields <T> from DB
     *
     * @return List<T>
     */
    List<T> findAll();

    /**
     * Create new field in table DB if there is not exist
     * and update if already exist
     *
     * @param source <T>
     * @return T element
     */
    T save(T source);

    /**
     * Delete field from table in DB by id
     *
     * @param id id in table
     */
    void remove(Long id);

}
