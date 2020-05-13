package ucentral.swii.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import ucentral.swii.entities.Examen;
import ucentral.swii.entities.Facultad;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-05-03T20:34:50")
@StaticMetamodel(Materia.class)
public class Materia_ { 

    public static volatile ListAttribute<Materia, Examen> examenList;
    public static volatile SingularAttribute<Materia, Long> idmateria;
    public static volatile SingularAttribute<Materia, String> nombre;
    public static volatile SingularAttribute<Materia, Facultad> facultad;

}