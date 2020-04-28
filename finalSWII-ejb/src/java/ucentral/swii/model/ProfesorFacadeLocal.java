/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ucentral.swii.model;

import java.util.List;
import javax.ejb.Local;
import ucentral.swii.entities.Profesor;

/**
 *
 * @author david
 */
@Local
public interface ProfesorFacadeLocal {

    //void create(Profesor profesor);

    //void edit(Profesor profesor);

    void remove(Profesor profesor);

    //Profesor find(Object id);

    //List<Profesor> findAll();

    //List<Profesor> findRange(int[] range);

    int count();
    
    void insertar(Profesor profesor);
    
    List<Profesor> getProfesores();
    
    long encontrarUsuario(Profesor profesor);
}
