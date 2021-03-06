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
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;
import ucentral.swii.entities.Estudiante;
import ucentral.swii.entities.Usuario;
import ucentral.swii.model.EstudianteFacade;
import ucentral.swii.model.EstudianteFacadeLocal;
import ucentral.swii.model.UsuarioFacadeLocal;
import ucentral.swii.utils.Util;

/**
 *
 * @author david
 */
@Named(value = "usuarioBean")
@SessionScoped
public class UsuarioBean implements Serializable {

    @EJB
    private UsuarioFacadeLocal usuarioFacade;
    @EJB
    private EstudianteFacadeLocal estudianteFacade;

    private List<SelectItem> estados;
    private Long idUsuario;
    private String nombreUsuario;
    private String contrasena;
    private String tipoUsuario;
    private String estado;
    @ManagedProperty(value = "#{param.pageId}")
    private String pageId;

    /**
     * Creates a new instance of UsuarioBean
     */
    public UsuarioBean() {
        llenarEstados();
    }

    private void llenarEstados() {
        estados = new ArrayList<>();
        estados.add(new SelectItem(Usuario.ESTADO_ACTIVO, Usuario.ESTADO_ACTIVO));
        estados.add(new SelectItem(Usuario.ESTADO_INACTIVO, Usuario.ESTADO_INACTIVO));
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

    public String validateUsernamePassword() {
        Usuario usuario = usuarioFacade.findByCredenciales(nombreUsuario, contrasena);
        boolean result = usuario == null;
        if (!result) {

            // get Http Session and store username
            HttpSession session = Util.getSession();
            session.setAttribute("usuario", usuario);
            String tipoUsuarios = usuario.getTipoUsuario();
            switch (tipoUsuarios) {
                case Usuario.TIPO_ADMIN:
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Login!",
                            "Admin"));
                    return tipoUsuarios;
                case Usuario.TIPO_PROFE:
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Login!",
                            "Profesor"));
                    return tipoUsuarios;
                case Usuario.TIPO_ESTUDIANTE:
                    return cambioDeRuta(usuario);
                default:
                    return "NO EXISTE ESE USUARIO";
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Invalid Login!",
                    "Please Try Again!"));

            // invalidate session, and redirect to other pages
            //message = "Invalid Login. Please Try Again!";
            return "login";
        }
    }

    public String registrar() {
        return "Registrar";
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

    private String cambioDeRuta(Usuario usuario) {
        Estudiante estudiante = estudianteFacade.encontrarEstudiante(usuario);
        if (estudiante != null) {
            if (estudiante.getPrimerIngreso()) {
                return "cambiar_contrasena";
            } else {
                return "ingresarEstudiante";
            }

        }
        return "no encontrado";
    }

}
