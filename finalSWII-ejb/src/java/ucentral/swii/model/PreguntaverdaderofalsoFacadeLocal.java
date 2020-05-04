/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ucentral.swii.model;

import java.util.List;
import javax.ejb.Local;
import ucentral.swii.entities.Preguntaverdaderofalso;

/**
 *
 * @author david
 */
@Local
public interface PreguntaverdaderofalsoFacadeLocal {

    //void create(Preguntaverdaderofalso preguntaverdaderofalso);

    void edit(Preguntaverdaderofalso preguntaverdaderofalso);

    void remove(Preguntaverdaderofalso preguntaverdaderofalso);

    Preguntaverdaderofalso find(Object id);

    List<Preguntaverdaderofalso> findAll();

    List<Preguntaverdaderofalso> findRange(int[] range);

    int count();
    
    void insertar(Preguntaverdaderofalso preguntaverdaderofalso);
    
}
