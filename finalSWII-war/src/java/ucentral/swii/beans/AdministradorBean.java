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
import ucentral.swii.entities.Administrador;
import ucentral.swii.entities.Estudiante;
import ucentral.swii.entities.Profesor;
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
    
    private List<Administrador> listaAdministradores;
    
    //Estudiante
    private List<Estudiante> listaEstudiantes;
    
    //Profesor
    private String nombreProfesor;
    private String apellidoProfesor;
    private String correoProfesor;
    private String telefonoProfesor;
    
    private List<Profesor> listaProfesores;

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

    /**
     * Creates a new instance of AdministradorBean
     */
    public AdministradorBean() {
        inicializar();
    }

    private void inicializar() {
        llenarEstados();
        correo = "";
        
        nombreProfesor = "";
        apellidoProfesor = "";
        correoProfesor = "";
        telefonoProfesor = "";

        usuario = null;
        nombreUsuario = "";
        contrasena = "";
        mostrarDialogo = false;
    }

    private void llenarEstados() {
        estados = new ArrayList<>();
        estados.add(new SelectItem(Usuario.ESTADO_ACTIVO, Usuario.ESTADO_ACTIVO));
        estados.add(new SelectItem(Usuario.ESTADO_INACTIVO, Usuario.ESTADO_INACTIVO));
    }

    public void cambiarEstado(ValueChangeEvent event) {
        estado = (String) event.getNewValue();
        System.out.println("MARCA2: " + estado);
    }

    public String crearUsuarioAdministrador() {
        try {
            tipoUsuario = Usuario.TIPO_ADMIN;
            //usuario = new Usuario(nombreUsuario, contrasena, tipoUsuario, estado);
            idUsuario = (long) usuarioFacade.ultimoRegistro();
            usuario = new Usuario(idUsuario, nombreUsuario, contrasena, tipoUsuario, estado);
            usuarioFacade.insertar(usuario);
            mostrarDialogo = false;
            System.out.println("**********************+MARCA1: " + usuario.getIdUsuario());
            crearAdministrador(usuario);

        } catch (Exception e) {
            inicializar();
            mostrarDialogo = false;
        }
        inicializar();
        return "correcto";
    }

    public void crearAdministrador(Usuario usuario) {
        Administrador administrador = new Administrador(correo);
        administrador.setUsuario(usuario);
        administradorFacade.insertar(administrador);
        System.out.println("**********************+MARCA3 ");
    }

    public List<Administrador> getAdministradores() {
        listaAdministradores = new ArrayList<>();
        listaAdministradores = administradorFacade.getAdministradores();
        completarListaAdministradores();
        return listaAdministradores;
    }

    private void completarListaAdministradores() {
        try {
            listaAdministradores.forEach((administrador) -> {
                long idUsuarioAux = administradorFacade.encontrarUsuario(administrador);
                Usuario usuarioAux = usuarioFacade.find(idUsuarioAux);
                administrador.setUsuario(usuarioAux);
            });
        } catch (Exception e) {
            System.out.println("ERROR" + e);
        }
    }

    public void eliminarAdministrador(Administrador administrador) {
        try {
            System.out.println("***********MARCA6");
            usuarioAux = new Usuario();
            usuarioAux = administrador.getUsuario();
            administradorFacade.remove(administrador);
            eliminarUsuario();
        } catch (Exception e) {
            System.out.println("ERROR" + e);
        }
    }

    public void eliminarUsuario() {
        try {
            usuarioFacade.remove(usuarioAux);
        } catch (Exception e) {
            System.out.println("ERROR" + e);
        }
    }
    
    public String crearUsuarioProfesor() {
        try {
            tipoUsuario = Usuario.TIPO_PROFE;
            //usuario = new Usuario(nombreUsuario, contrasena, tipoUsuario, estado);
            idUsuario = (long) usuarioFacade.ultimoRegistro();
            usuario = new Usuario(idUsuario, generarNombreUsuario(), contrasena, tipoUsuario, estado);
            usuarioFacade.insertar(usuario);
            mostrarDialogo = false;
            System.out.println("**********************+MARCA9: " + usuario.getIdUsuario());
            crearProfesor(usuario);

        } catch (Exception e) {
            inicializar();
            mostrarDialogo = false;
        }
        inicializar();
        return "correcto";
    }
    
    private String generarNombreUsuario(){
        
        String usuarioGenerado = ""+nombreProfesor.charAt(0)+""+apellidoProfesor
                +""+idUsuario;
        
        return usuarioGenerado;
    }
    
    public void crearProfesor(Usuario usuario) {
        Profesor profesor = new Profesor(nombreProfesor, apellidoProfesor, correoProfesor, telefonoProfesor);
        profesor.setUsuario(usuario);
        profesorFacade.insertar(profesor);
        System.out.println("**********************+MARCA10 ");
    }
    
    public List<Profesor> getProfesores() {
        listaProfesores = new ArrayList<>();
        listaProfesores = profesorFacade.getProfesores();
        completarListaProfesores();
        return listaProfesores;
    }
    
    private void completarListaProfesores() {
        try {
            listaProfesores.forEach((profesor) -> {
                long idUsuarioAux = profesorFacade.encontrarUsuario(profesor);
                Usuario usuarioAux = usuarioFacade.find(idUsuarioAux);
                profesor.setUsuario(usuarioAux);
            });
        } catch (Exception e) {
            System.out.println("ERROR" + e);
        }
    }
    
    public void eliminarProfesor(Profesor profesor) {
        try {
            System.out.println("***********MARCA6");
            usuarioAux = new Usuario();
            usuarioAux = profesor.getUsuario();
            profesorFacade.remove(profesor);
            eliminarUsuario();
        } catch (Exception e) {
            System.out.println("ERROR" + e);
        }
    }
    
    public List<Estudiante> getEstudiantes() {
        listaEstudiantes = new ArrayList<>();
        listaEstudiantes = estudianteFacade.getEstudiantes();
        completarListaEstudiantes();
        return listaEstudiantes;
    }
    
    private void completarListaEstudiantes() {
        try {
            listaEstudiantes.forEach((estudiante) -> {
                long idUsuarioAux = estudianteFacade.encontrarUsuario(estudiante);
                Usuario usuarioAux = usuarioFacade.find(idUsuarioAux);
                estudiante.setUsuario(usuarioAux);
            });
        } catch (Exception e) {
            System.out.println("ERROR" + e);
        }
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

    public List<Administrador> getListaAdministradores() {
        return listaAdministradores;
    }

    public void setListaAdministradores(List<Administrador> listaAdministradores) {
        this.listaAdministradores = listaAdministradores;
    }

    public String getNombreProfesor() {
        return nombreProfesor;
    }

    public void setNombreProfesor(String nombreProfesor) {
        this.nombreProfesor = nombreProfesor;
    }

    public String getApellidoProfesor() {
        return apellidoProfesor;
    }

    public void setApellidoProfesor(String apellidoProfesor) {
        this.apellidoProfesor = apellidoProfesor;
    }

    public String getCorreoProfesor() {
        return correoProfesor;
    }

    public void setCorreoProfesor(String correoProfesor) {
        this.correoProfesor = correoProfesor;
    }

    public String getTelefonoProfesor() {
        return telefonoProfesor;
    }

    public void setTelefonoProfesor(String telefonoProfesor) {
        this.telefonoProfesor = telefonoProfesor;
    }

    public List<Profesor> getListaProfesores() {
        return listaProfesores;
    }

    public void setListaProfesores(List<Profesor> listaProfesores) {
        this.listaProfesores = listaProfesores;
    }

    public List<Estudiante> getListaEstudiantes() {
        return listaEstudiantes;
    }

    public void setListaEstudiantes(List<Estudiante> listaEstudiantes) {
        this.listaEstudiantes = listaEstudiantes;
    }
}
