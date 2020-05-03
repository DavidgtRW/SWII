/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ucentral.swii.utilidades;

import javax.ejb.Local;

/**
 *
 * @author Oshin
 */
@Local
public interface UtilidadesLocal {
     void enviarCorreo (String correo, String usuatrio, String contrasena, String nombre);
     
     String generarContra();
}
