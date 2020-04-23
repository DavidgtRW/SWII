/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ucentral.swii.beans;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.model.SelectItem;
import ucentral.swii.entities.Usuario;
import ucentral.swii.model.UsuarioFacadeLocal;

/**
 *
 * @author david
 */
@Named(value = "usuarioBean")
@SessionScoped
public class UsuarioBean implements Serializable {

    @EJB
    private UsuarioFacadeLocal usuarioFacade;
    
    private List<SelectItem> estados;

    private Long idUsuario;
    private String nombreUsuario;
    private String contrasena;
    private String tipoUsuario;
    private String estado;

    /**
     * Creates a new instance of UsuarioBean
     */
    public UsuarioBean() {
        llenarEstados();
    }

    private void llenarEstados(){
        estados = new ArrayList<>();
        estados.add(new SelectItem("Activo", "ACTIVO"));
        estados.add(new SelectItem("Inactivo", "INACTIVO"));
    }
    public String redirInicio() {
        return "inicio";
    }

    public List<Usuario> getUsuarios() {
        return usuarioFacade.getUsuarios();
    }

    public void crear() {
        try {
            Usuario usuario = new Usuario();
            usuario.setIdUsuario(Long.valueOf(idUsuario));
            usuario.setNombreUsuario(nombreUsuario);
            usuario.setContrasena(contrasena);
            usuario.setTipoUsuario(tipoUsuario);
            usuario.setEstado(estado);
            usuarioFacade.insertar(usuario);
        } catch (Exception e) {
            
        }

    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<SelectItem> getEstados() {
        return estados;
    }

    public void setEstados(List<SelectItem> estados) {
        this.estados = estados;
    }
    
    
}
