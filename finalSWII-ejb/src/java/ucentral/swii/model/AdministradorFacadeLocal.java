/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ucentral.swii.model;

import java.util.List;
import javax.ejb.Local;
import ucentral.swii.entities.Administrador;

/**
 *
 * @author david
 */
@Local
public interface AdministradorFacadeLocal {

    //void create(Administrador administrador);

    //void edit(Administrador administrador);

    void remove(Administrador administrador);

    //Administrador find(Object id);

    //List<Administrador> findAll();

    //List<Administrador> findRange(int[] range);

    int count();
    
    void insertar(Administrador administrador);
    
    List<Administrador> getAdministradores();
    
    long encontrarUsuario(Administrador administrador);
    
}
