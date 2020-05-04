/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ucentral.swii.model;

import java.util.List;
import javax.ejb.Local;
import ucentral.swii.entities.Examen;

/**
 *
 * @author david
 */
@Local
public interface ExamenFacadeLocal {

    //void create(Examen examen);

    void edit(Examen examen);

    void remove(Examen examen);

    Examen find(Object id);

    List<Examen> findAll();

    List<Examen> findRange(int[] range);

    int count();
    
    void insertar(Examen examen);
        
    int ultimoRegistro();
    
}
