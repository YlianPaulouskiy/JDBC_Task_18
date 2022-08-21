package edu.practice.triangles.model;

import edu.practice.triangles.model.parent.BaseModel;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
/**
 * Model of Triangle
 */
public class Triangle extends BaseModel {

    private Point leftPoint;
    private Point midPoint;
    private Point rightPoint;

}
