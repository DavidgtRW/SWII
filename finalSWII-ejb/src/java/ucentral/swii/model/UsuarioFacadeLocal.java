/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ucentral.swii.model;

import java.util.List;
import javax.ejb.Local;
import ucentral.swii.entities.Usuario;

/**
 *
 * @author david
 */
@Local
public interface UsuarioFacadeLocal {

    //void create(Usuario usuario);

    //void edit(Usuario usuario);

    void remove(Usuario usuario);

    Usuario find(long id);

    //List<Usuario> findAll();

    //List<Usuario> findRange(int[] range);

    int count();
    
    void insertar(Usuario usuario);
    
    List<Usuario> getUsuarios();
    
    int ultimoRegistro();
    
    Usuario findByCredenciales(String usuario, String contraseña);
    
}
