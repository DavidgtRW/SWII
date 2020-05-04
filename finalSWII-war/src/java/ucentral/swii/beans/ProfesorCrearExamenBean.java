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
import javax.faces.event.ActionListener;
import javax.faces.event.ValueChangeEvent;
import ucentral.swii.entities.Examen;
import ucentral.swii.entities.Materia;
import ucentral.swii.entities.Preguntaverdaderofalso;
import ucentral.swii.manejodatoscrearexamen.PreguntaVerdaderoOFalso;
import ucentral.swii.model.ExamenFacadeLocal;
import ucentral.swii.model.MateriaFacade;
import ucentral.swii.model.MateriaFacadeLocal;
import ucentral.swii.model.PreguntaverdaderofalsoFacadeLocal;

/**
 *
 * @author david
 */
@Named(value = "profesorCrearExamenBean")
@SessionScoped
public class ProfesorCrearExamenBean implements Serializable {

    @EJB
    private ExamenFacadeLocal examenFacadeLocal;

    @EJB
    private MateriaFacadeLocal materiaFacadeLocal;

    @EJB
    private PreguntaverdaderofalsoFacadeLocal preguntaverdaderofalsoFacadeLocal;

    /*Materia*/
    private List<Materia> lstMaterias;
    private Materia materia;

    /* Examen */
    private String nombreExamen;
    private Long idMateria;

    private int numeroPreguntasVerdaderoOFalso;
    private List<String> numeroPreguntas;

    private String respVerdaderoOFalso;

    private List<PreguntaVerdaderoOFalso> lstPreguntaVerdaderoOFalsos;

    private boolean mostrarTablaPreguntasVerdaderoOFalso;
    private boolean mostrarBotonAceptar;

    private List<String> verdaderoOFalso;

    /**
     * Creates a new instance of ProfesorCrearExamenBean
     */
    public ProfesorCrearExamenBean() {
        inicializar();

    }

    private void inicializar() {
        nombreExamen = "";
        llenarNumeroPreguntas();
        llenarVerdaderoOFalso();
        lstPreguntaVerdaderoOFalsos = new ArrayList<>();
        mostrarTablaPreguntasVerdaderoOFalso = false;
        mostrarBotonAceptar = true;

    }

    private void llenarVerdaderoOFalso() {
        verdaderoOFalso = new ArrayList<>();
        verdaderoOFalso.add("Verdadero");
        verdaderoOFalso.add("Falso");
    }

    private void llenarNumeroPreguntas() {
        //options
        numeroPreguntas = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            numeroPreguntas.add(String.valueOf(i + 1));
        }
    }

    private List<Materia> llenarLstMaterias() {
        try {

            lstMaterias = new ArrayList<>();
            lstMaterias = materiaFacadeLocal.findAll();
        } catch (Exception e) {
            System.out.println("ERROR");
        }
        return lstMaterias;
    }

    public void finalizar(ActionListener actionListener) {
        try {
            Examen examenAux = new Examen();
            examenAux.setIdexamen(Long.valueOf(examenFacadeLocal.ultimoRegistro()));
            examenAux.setNombre(nombreExamen);
            examenAux.setMateria(materiaFacadeLocal.find(idMateria));
            examenFacadeLocal.insertar(examenAux);

            for (PreguntaVerdaderoOFalso preguntaVerdaderoOFalso : lstPreguntaVerdaderoOFalsos) {
                Preguntaverdaderofalso preguntaverdaderofalsoAux = new Preguntaverdaderofalso();
                preguntaverdaderofalsoAux.setEnunciado(preguntaVerdaderoOFalso.getEnunciado());
                preguntaverdaderofalsoAux.setRespuesta(preguntaVerdaderoOFalso.getRespuesta());
                preguntaverdaderofalsoAux.setExamen(examenAux);
                preguntaverdaderofalsoFacadeLocal.insertar(preguntaverdaderofalsoAux);
            }

        } catch (Exception e) {
            System.err.println("Error"+e);
            inicializar();
        }

        inicializar();
    }

    public void mostrarTabla(ActionListener actionListener) {

        llenarLstMaterias();
        llenarListaPreguntas(actionListener);
        mostrarBotonAceptar = false;
        mostrarTablaPreguntasVerdaderoOFalso = true;
    }

    /*
    public void cambiarVerOFalso(PreguntaVerdaderoOFalso preguntaVerdaderoOFalso) {
        try {
            preguntaVerdaderoOFalso.setRespuesta(respVerdaderoOFalso);
        } catch (Exception e) {
            System.out.println("MARCA21: " + respVerdaderoOFalso);
        }
    }*/
    public void cambiarNumeroPreguntas(ValueChangeEvent event) {
        String numeroPreguntasAux = (String) event.getNewValue();
        numeroPreguntasVerdaderoOFalso = Integer.valueOf(numeroPreguntasAux);
        System.out.println("MARCA20: " + numeroPreguntasVerdaderoOFalso);
    }

    public void llenarListaPreguntas(ActionListener actionListener) {

        for (int i = 0; i < numeroPreguntasVerdaderoOFalso; i++) {
            PreguntaVerdaderoOFalso preguntaVerdaderoOFalso = new PreguntaVerdaderoOFalso();
            preguntaVerdaderoOFalso.setNumeroPregunta(i + 1);
            preguntaVerdaderoOFalso.setEnunciado("");
            preguntaVerdaderoOFalso.setRespuesta("");
            lstPreguntaVerdaderoOFalsos.add(preguntaVerdaderoOFalso);
        }

    }

    public String getNombreExamen() {
        return nombreExamen;
    }

    public void setNombreExamen(String nombreExamen) {
        this.nombreExamen = nombreExamen;
    }

    public int getNumeroPreguntasVerdaderoOFalso() {
        return numeroPreguntasVerdaderoOFalso;
    }

    public void setNumeroPreguntasVerdaderoOFalso(int numeroPreguntasVerdaderoOFalso) {
        this.numeroPreguntasVerdaderoOFalso = numeroPreguntasVerdaderoOFalso;
    }

    public List<String> getNumeroPreguntas() {
        return numeroPreguntas;
    }

    public void setNumeroPreguntas(List<String> numeroPreguntas) {
        this.numeroPreguntas = numeroPreguntas;
    }

    public List<PreguntaVerdaderoOFalso> getLstPreguntaVerdaderoOFalsos() {
        return lstPreguntaVerdaderoOFalsos;
    }

    public void setLstPreguntaVerdaderoOFalsos(List<PreguntaVerdaderoOFalso> lstPreguntaVerdaderoOFalsos) {
        this.lstPreguntaVerdaderoOFalsos = lstPreguntaVerdaderoOFalsos;
    }

    public boolean isMostrarTablaPreguntasVerdaderoOFalso() {
        System.out.println("MARCA 22: " + mostrarTablaPreguntasVerdaderoOFalso);
        return mostrarTablaPreguntasVerdaderoOFalso;
    }

    public void setMostrarTablaPreguntasVerdaderoOFalso(boolean mostrarTablaPreguntasVerdaderoOFalso) {
        this.mostrarTablaPreguntasVerdaderoOFalso = mostrarTablaPreguntasVerdaderoOFalso;
    }

    public boolean isMostrarBotonAceptar() {
        return mostrarBotonAceptar;
    }

    public void setMostrarBotonAceptar(boolean mostrarBotonAceptar) {
        this.mostrarBotonAceptar = mostrarBotonAceptar;
    }

    public String getRespVerdaderoOFalso() {
        return respVerdaderoOFalso;
    }

    public void setRespVerdaderoOFalso(String respVerdaderoOFalso) {
        this.respVerdaderoOFalso = respVerdaderoOFalso;
    }

    public List<String> getVerdaderoOFalso() {
        return verdaderoOFalso;
    }

    public void setVerdaderoOFalso(List<String> verdaderoOFalso) {
        this.verdaderoOFalso = verdaderoOFalso;
    }

    public List<Materia> getLstMaterias() {
        return lstMaterias;
    }

    public void setLstMaterias(List<Materia> lstMaterias) {
        this.lstMaterias = lstMaterias;
    }

    public Long getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(Long idMateria) {
        this.idMateria = idMateria;
    }

}
