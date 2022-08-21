package edu.practice.triangles.thread;

import edu.practice.triangles.logic.TriangleLogic;
import edu.practice.triangles.logic.impl.TriangleLogicImpl;
import edu.practice.triangles.model.Triangle;
import edu.practice.triangles.service.OtherTriangleService;
import edu.practice.triangles.service.TriangleService;
import edu.practice.triangles.service.impl.OtherTriangleServiceImpl;
import edu.practice.triangles.service.impl.TriangleServiceImpl;

import java.util.List;

/**
 * Thread Equilateral triangle
 */
public class EquilateralCheckThread extends Thread{

    private final TriangleService service;
    private final OtherTriangleService otherService;
    private final TriangleLogic logic;

    //------------------------------------------------------------------------------------------------------------------

    public EquilateralCheckThread(String name) {
        super(name);
        service = new TriangleServiceImpl();
        otherService = new OtherTriangleServiceImpl();
        logic = new TriangleLogicImpl();
    }

    /**
     * Check is triangle Equilateral.
     * if There is to add this triangle in equilateral_triangles table
     */
    @Override
    public void run() {
        List<Triangle> triangleList = service.findAll();
        String table = "equilateral_triangles";
        for (Triangle triangle : triangleList) {
            if (logic.isEquilateral(triangle)) {
                otherService.save(table, triangle);
            }
        }
        triangleList = otherService.findAll(table);
        for (Triangle triangle : triangleList) {
            if (!logic.isEquilateral(triangle)) {
                otherService.remove(table, triangle.getId());
            }
        }
    }

    //------------------------------------------------------------------------------------------------------------------

}
