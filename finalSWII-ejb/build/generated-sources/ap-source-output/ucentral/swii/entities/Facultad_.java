package ucentral.swii.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import ucentral.swii.entities.Materia;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-05-03T20:34:50")
@StaticMetamodel(Facultad.class)
public class Facultad_ { 

    public static volatile SingularAttribute<Facultad, Long> idfacultad;
    public static volatile ListAttribute<Facultad, Materia> materiaList;
    public static volatile SingularAttribute<Facultad, String> nombre;

}