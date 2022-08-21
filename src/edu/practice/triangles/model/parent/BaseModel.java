package edu.practice.triangles.model.parent;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
/**
 * Base Model keeps fields by all models
 */
public abstract class BaseModel {

    private Long id;
    private Date dateCreation;
    private Date lastModified;
    private Long version;

}
