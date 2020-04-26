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
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import ucentral.swii.entities.Usuario;
import ucentral.swii.model.AdministradorFacadeLocal;
import ucentral.swii.model.EstudianteFacadeLocal;
import ucentral.swii.model.ProfesorFacadeLocal;
import ucentral.swii.model.UsuarioFacadeLocal;

/**
 *
 * @author david
 */
@Named(value = "administradorBean")
@SessionScoped
public class AdministradorBean implements Serializable {

    @EJB
    private ProfesorFacadeLocal profesorFacade;
    @EJB
    private AdministradorFacadeLocal administradorFacade;
    @EJB
    private EstudianteFacadeLocal estudianteFacade;
    @EJB
    private UsuarioFacadeLocal usuarioFacade;

    //Administrador
    private String correo;
    private Usuario usuario;

    //Usuario
    private String nombreUsuario;
    private String contrasena;
    private String tipoUsuario;
    private String estado;
    private List<SelectItem> estados;

    //Util
    private String confirmarContrasena;
    private boolean mostrarDialogo;

    /**
     * Creates a new instance of AdministradorBean
     */
    public AdministradorBean() {
        inicializar();
    }

    private void inicializar() {
        llenarEstados();
        correo="";
        
        
        nombreUsuario = "";
        contrasena = "";
        mostrarDialogo = false;
    }

    private void llenarEstados() {
        estados = new ArrayList<>();
        estados.add(new SelectItem(Usuario.ESTADO_ACTIVO, Usuario.ESTADO_ACTIVO));
        estados.add(new SelectItem(Usuario.ESTADO_INACTIVO, Usuario.ESTADO_INACTIVO));
    }
    
    public void cambiarEstado(ValueChangeEvent event){
        estado = (String) event.getNewValue();
        System.out.println("MARCA2"+estado);
    }

    public String crearUsuario() {
        try {
            tipoUsuario = Usuario.TIPO_ADMIN;
            usuario = new Usuario(nombreUsuario, contrasena, tipoUsuario, estado);
            usuarioFacade.insertar(usuario);
            mostrarDialogo = false;
            System.out.println("MARCA1");
        } catch (Exception e) {
            inicializar();
            mostrarDialogo = false;
        }

        return "correcto";
    }
    
    public void crearAdministrador(Usuario usuario){
        
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getConfirmarContrasena() {
        return confirmarContrasena;
    }

    public void setConfirmarContrasena(String confirmarContrasena) {
        this.confirmarContrasena = confirmarContrasena;
    }

    public boolean isMostrarDialogo() {
        return mostrarDialogo;
    }

    public void setMostrarDialogo(boolean mostrarDialogo) {
        this.mostrarDialogo = mostrarDialogo;
    }

}
