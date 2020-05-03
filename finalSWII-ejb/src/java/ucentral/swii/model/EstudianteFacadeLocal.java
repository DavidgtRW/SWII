/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ucentral.swii.model;

import java.util.List;
import javax.ejb.Local;
import ucentral.swii.entities.Estudiante;
import ucentral.swii.entities.Usuario;

/**
 *
 * @author david
 */
@Local
public interface EstudianteFacadeLocal {

    //void create(Estudiante estudiante);
    //void edit(Estudiante estudiante);
    void remove(Estudiante estudiante);

    //Estudiante find(Object id);
    //List<Estudiante> findAll();
    //List<Estudiante> findRange(int[] range);
    int count();

    void insertar(Estudiante estudiante);

    List<Estudiante> getEstudiantes();

    long encontrarUsuario(Estudiante estudiante);

    Estudiante encontrarEstudiante(Usuario usuario);

    void updateEstudianteContrasena(String contrasena, Long id);
    
    void updateEstudianteIngreso(boolean ingreso, Long id);

}
