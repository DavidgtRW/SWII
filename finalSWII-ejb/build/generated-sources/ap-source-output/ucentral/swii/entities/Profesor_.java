package ucentral.swii.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import ucentral.swii.entities.Usuario;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-05-03T20:34:50")
@StaticMetamodel(Profesor.class)
public class Profesor_ { 

    public static volatile SingularAttribute<Profesor, Long> idProfesor;
    public static volatile SingularAttribute<Profesor, String> apellido;
    public static volatile SingularAttribute<Profesor, String> correo;
    public static volatile SingularAttribute<Profesor, Usuario> usuario;
    public static volatile SingularAttribute<Profesor, String> telefono;
    public static volatile SingularAttribute<Profesor, String> nombre;

}