/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ucentral.swii.model;

import java.util.List;
import javax.ejb.Local;
import ucentral.swii.entities.Respuestaopcionmultiple;

/**
 *
 * @author david
 */
@Local
public interface RespuestaopcionmultipleFacadeLocal {

    //void create(Respuestaopcionmultiple respuestaopcionmultiple);

    void edit(Respuestaopcionmultiple respuestaopcionmultiple);

    void remove(Respuestaopcionmultiple respuestaopcionmultiple);

    Respuestaopcionmultiple find(Object id);

    List<Respuestaopcionmultiple> findAll();

    List<Respuestaopcionmultiple> findRange(int[] range);

    int count();
    
    void insertar(Respuestaopcionmultiple respuestaopcionmultiple);
        
    int ultimoRegistro();
    
}
