package ucentral.swii.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import ucentral.swii.entities.Usuario;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-05-03T20:34:50")
@StaticMetamodel(Estudiante.class)
public class Estudiante_ { 

    public static volatile SingularAttribute<Estudiante, String> apellido;
    public static volatile SingularAttribute<Estudiante, String> correo;
    public static volatile SingularAttribute<Estudiante, Long> idEstudiante;
    public static volatile SingularAttribute<Estudiante, Usuario> usuario;
    public static volatile SingularAttribute<Estudiante, String> telefono;
    public static volatile SingularAttribute<Estudiante, String> nombre;
    public static volatile SingularAttribute<Estudiante, Boolean> primerIngreso;

}