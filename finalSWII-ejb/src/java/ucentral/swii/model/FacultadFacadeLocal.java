/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ucentral.swii.model;

import java.util.List;
import javax.ejb.Local;
import ucentral.swii.entities.Facultad;

/**
 *
 * @author david
 */
@Local
public interface FacultadFacadeLocal {

    void create(Facultad facultad);

    void edit(Facultad facultad);

    void remove(Facultad facultad);

    Facultad find(Object id);

    List<Facultad> findAll();

    List<Facultad> findRange(int[] range);

    int count();
    
}
