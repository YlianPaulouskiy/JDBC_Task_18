package edu.practice.triangles.service.impl;

import edu.practice.triangles.dao.OtherTriangleDao;
import edu.practice.triangles.dao.impl.OtherTriangleDaoImpl;
import edu.practice.triangles.exception.TriangleNotCreatedException;
import edu.practice.triangles.exception.TriangleNotFoundException;
import edu.practice.triangles.model.Triangle;
import edu.practice.triangles.service.OtherTriangleService;

import java.util.List;

/**
 * Implement OtherTriangleService
 */
public class OtherTriangleServiceImpl implements OtherTriangleService {

    private final OtherTriangleDao otherTriangleDao;

    public OtherTriangleServiceImpl() {
        otherTriangleDao = new OtherTriangleDaoImpl();
    }

    //------------------------------------------------------------------------------------------------------------------

    @Override
    public Triangle findOne(String table, Long id) {
        return otherTriangleDao.findOne(table, id).orElseThrow(TriangleNotFoundException::new);
    }

    @Override
    public List<Triangle> findAll(String table) {
        return otherTriangleDao.findAll(table);
    }

    @Override
    public Triangle save(String table, Triangle source) {
        return otherTriangleDao.create(table, source).orElseThrow(TriangleNotCreatedException::new);
    }

    @Override
    public void remove(String table, Long id) {
        otherTriangleDao.remove(table, id);
    }



}
