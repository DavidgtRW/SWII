/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ucentral.swii.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import ucentral.swii.entities.Estudiante;
import ucentral.swii.entities.Usuario;
import ucentral.swii.model.AdministradorFacadeLocal;
import ucentral.swii.model.EstudianteFacadeLocal;
import ucentral.swii.model.ProfesorFacadeLocal;
import ucentral.swii.model.UsuarioFacadeLocal;
import ucentral.swii.utilidades.UtilidadesFacade;
import ucentral.swii.utils.Util;

/**
 *
 * @author Oshin
 */
@Named(value = "estudianteBean")
@SessionScoped
public class EstudianteBean implements Serializable {

    @EJB
    private EstudianteFacadeLocal estudianteFacade;
    @EJB
    private UsuarioFacadeLocal usuarioFacade;
    @EJB
    private UtilidadesFacade utilFacade;

    //estudiante
    private Long idEstudiante;
    private String nombre;
    private String apellido;
    private String correo;
    private String telefono;
    private boolean primerRegistro;

    //Usuario
    private Long idUsuario;
    private String nombreUsuario;
    private String contrasena;
    private String tipoUsuario;
    private String estado;
    private List<SelectItem> estados;

    //Util
    private String confirmarContrasena;
    private boolean mostrarDialogo;
    private Usuario usuarioAux;

    //Comun
    private Usuario usuario;

    public EstudianteBean() {
        inicializar();
    }

    private void inicializar() {
        correo = "";
        nombre = "";
        apellido = "";
        correo = "";
        telefono = "";
        primerRegistro = false;

        usuario = null;
        nombreUsuario = "";
        contrasena = "";
        mostrarDialogo = false;
    }


    private String generarNombreUsuario() {

        String usuarioGenerado = "" + nombre.charAt(0) + "" + apellido
                + "" + idUsuario;

        return usuarioGenerado;
    }

    public String crearUsuarioEstudiante() {
        try {
            tipoUsuario = Usuario.TIPO_ESTUDIANTE;
            //usuario = new Usuario(nombreUsuario, contrasena, tipoUsuario, estado);
            idUsuario = (long) usuarioFacade.ultimoRegistro();
            String nombreUsu = generarNombreUsuario();
            contrasena = utilFacade.generarContra();
            usuario = new Usuario(idUsuario, nombreUsu, contrasena, tipoUsuario, Usuario.ESTADO_ACTIVO);
            usuarioFacade.insertar(usuario);
            mostrarDialogo = false;
            System.out.println("**********************+MARCA9: " + usuario.getIdUsuario());
            crearEstudiante(usuario);

            utilFacade.enviarCorreo(correo, nombreUsu, contrasena, nombre);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Se a enviado un correo revisalo !",
                            "registro completo"));
        } catch (Exception e) {
            inicializar();
            mostrarDialogo = false;
        }
        inicializar();
        return "correcto";
    }

    public void crearEstudiante(Usuario usuario) {
        Estudiante estudiante = new Estudiante(idUsuario, nombre, apellido, correo);
        estudiante.setTelefono(telefono);
        estudiante.setPrimerIngreso(true);
        estudiante.setUsuario(usuario);
        estudianteFacade.insertar(estudiante);

        System.out.println("**********************+MARCA10 ");
    }
    
    public String actualizarContrasena(){
    
    if(contrasena.equals(confirmarContrasena)){
        // get Http Session and store username
            HttpSession session = Util.getSession();
       Usuario usu =  (Usuario) session.getAttribute("usuario");
        estudianteFacade.updateEstudianteContrasena(contrasena, usu.getIdUsuario());
        estudianteFacade.updateEstudianteIngreso(false, idUsuario);
    return "correcto";
    }else{
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "las contraseñas no son iguales!",
                    "Please Try Again!"));

            // invalidate session, and redirect to other pages
            //message = "Invalid Login. Please Try Again!";
            return "login";
    }
    
    }
    public String ingresar(){
        return "ingresar";
    }

    public EstudianteFacadeLocal getEstudianteFacade() {
        return estudianteFacade;
    }

    public void setEstudianteFacade(EstudianteFacadeLocal estudianteFacade) {
        this.estudianteFacade = estudianteFacade;
    }

    public UsuarioFacadeLocal getUsuarioFacade() {
        return usuarioFacade;
    }

    public void setUsuarioFacade(UsuarioFacadeLocal usuarioFacade) {
        this.usuarioFacade = usuarioFacade;
    }

    public Long getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(Long idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public boolean isPrimerRegistro() {
        return primerRegistro;
    }

    public void setPrimerRegistro(boolean primerRegistro) {
        this.primerRegistro = primerRegistro;
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

    public Usuario getUsuarioAux() {
        return usuarioAux;
    }

    public void setUsuarioAux(Usuario usuarioAux) {
        this.usuarioAux = usuarioAux;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
