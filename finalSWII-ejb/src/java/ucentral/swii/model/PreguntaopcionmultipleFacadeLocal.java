/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ucentral.swii.model;

import java.util.List;
import javax.ejb.Local;
import ucentral.swii.entities.Preguntaopcionmultiple;

/**
 *
 * @author david
 */
@Local
public interface PreguntaopcionmultipleFacadeLocal {

    //void create(Preguntaopcionmultiple preguntaopcionmultiple);

    void edit(Preguntaopcionmultiple preguntaopcionmultiple);

    void remove(Preguntaopcionmultiple preguntaopcionmultiple);

    Preguntaopcionmultiple find(Object id);

    List<Preguntaopcionmultiple> findAll();

    List<Preguntaopcionmultiple> findRange(int[] range);

    int count();
    
    void insertar(Preguntaopcionmultiple preguntaopcionmultiple);
        
    int ultimoRegistro();
    
}
