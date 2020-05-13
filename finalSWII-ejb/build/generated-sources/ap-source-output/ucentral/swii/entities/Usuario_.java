package ucentral.swii.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import ucentral.swii.entities.Administrador;
import ucentral.swii.entities.Estudiante;
import ucentral.swii.entities.Profesor;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-05-03T20:34:50")
@StaticMetamodel(Usuario.class)
public class Usuario_ { 

    public static volatile SingularAttribute<Usuario, String> estado;
    public static volatile ListAttribute<Usuario, Administrador> administradorList;
    public static volatile SingularAttribute<Usuario, Long> idUsuario;
    public static volatile ListAttribute<Usuario, Estudiante> estudianteList;
    public static volatile SingularAttribute<Usuario, String> contrasena;
    public static volatile SingularAttribute<Usuario, String> tipoUsuario;
    public static volatile SingularAttribute<Usuario, String> nombreUsuario;
    public static volatile ListAttribute<Usuario, Profesor> profesorList;

}