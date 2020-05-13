package ucentral.swii.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import ucentral.swii.entities.Materia;
import ucentral.swii.entities.Preguntaverdaderofalso;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-05-03T20:34:50")
@StaticMetamodel(Examen.class)
public class Examen_ { 

    public static volatile ListAttribute<Examen, Preguntaverdaderofalso> preguntaverdaderofalsoList;
    public static volatile SingularAttribute<Examen, Materia> materia;
    public static volatile SingularAttribute<Examen, String> nombre;
    public static volatile SingularAttribute<Examen, Long> idexamen;

}