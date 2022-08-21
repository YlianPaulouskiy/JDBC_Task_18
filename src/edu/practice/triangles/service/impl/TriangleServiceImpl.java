package edu.practice.triangles.service.impl;

import edu.practice.triangles.dao.TriangleDao;
import edu.practice.triangles.dao.impl.TriangleDaoImpl;
import edu.practice.triangles.exception.TriangleNotCreatedException;
import edu.practice.triangles.exception.TriangleNotFoundException;
import edu.practice.triangles.exception.TriangleNotUpdatedException;
import edu.practice.triangles.model.Triangle;
import edu.practice.triangles.service.TriangleService;

import java.util.List;

/**
 * Implement TriangleService
 */
public class TriangleServiceImpl implements TriangleService {

    private final TriangleDao triangleDao;

    public TriangleServiceImpl() {
        triangleDao = new TriangleDaoImpl();
    }

    @Override
    public Triangle findOne(Long id) {
        return triangleDao.findOne(id).orElseThrow(TriangleNotFoundException::new);
    }

    @Override
    public List<Triangle> findAll() {
        return triangleDao.findAll();
    }

    @Override
    public Triangle save(Triangle source) {
        return source.getId() == null ?
                triangleDao.create(source).orElseThrow(TriangleNotCreatedException::new) :
                triangleDao.update(source).orElseThrow(TriangleNotUpdatedException::new);
    }

    @Override
    public void remove(Long id) {
        triangleDao.remove(id);
    }
}
