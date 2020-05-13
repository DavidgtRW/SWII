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
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;
import javax.faces.event.ValueChangeEvent;
import ucentral.swii.entities.Examen;
import ucentral.swii.entities.Materia;
import ucentral.swii.entities.Preguntaopcionmultiple;
import ucentral.swii.entities.Preguntaverdaderofalso;
import ucentral.swii.entities.Respuestaopcionmultiple;
import ucentral.swii.manejodatoscrearexamen.PreguntaOpcionMultiple;
import ucentral.swii.manejodatoscrearexamen.PreguntaVerdaderoOFalso;
import ucentral.swii.manejodatoscrearexamen.Respuesta;
import ucentral.swii.model.ExamenFacadeLocal;
import ucentral.swii.model.MateriaFacade;
import ucentral.swii.model.MateriaFacadeLocal;
import ucentral.swii.model.PreguntaopcionmultipleFacade;
import ucentral.swii.model.PreguntaopcionmultipleFacadeLocal;
import ucentral.swii.model.PreguntaverdaderofalsoFacadeLocal;
import ucentral.swii.model.RespuestaopcionmultipleFacadeLocal;

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
    
    @EJB
    private PreguntaopcionmultipleFacadeLocal preguntaopcionmultipleFacadeLocal;
    
    @EJB
    private RespuestaopcionmultipleFacadeLocal respuestaopcionmultipleFacadeLocal;
    /*Materia*/
    private List<Materia> lstMaterias;
    private Materia materia;

    /* Examen */
    private String nombreExamen;
    private Long idMateria;

    private int numeroPreguntasVerdaderoOFalso;
    private List<String> numeroPreguntas;
    
    private int numeroPreguntasOpcionMultiple;
    private List<String> numeroPreguntasMultiple;

    private String respVerdaderoOFalso;

    private List<PreguntaVerdaderoOFalso> lstPreguntaVerdaderoOFalsos;
    
    private List<PreguntaOpcionMultiple> lstPreguntaOpcionMultiple;

    private boolean mostrarTablaPreguntasVerdaderoOFalso;
    private boolean mostrarBotonAceptar;

    private List<String> verdaderoOFalso;
    
    private List<String> valorCorrecto;
    
    private String enunciadoRespuesta1;
    private String valorRespuesta1;
    private String enunciadoRespuesta2;
    private String valorRespuesta2;
    private String enunciadoRespuesta3;
    private String valorRespuesta3;
    private String enunciadoRespuesta4;
    private String valorRespuesta4;
    
    private PreguntaOpcionMultiple preguntaOpcionMultiple;

    /**
     * Creates a new instance of ProfesorCrearExamenBean
     */
    public ProfesorCrearExamenBean() {
        inicializar();

    }

    private void inicializar() {
        preguntaOpcionMultiple = new PreguntaOpcionMultiple();
        nombreExamen = "";
        enunciadoRespuesta1 = "";
        valorRespuesta1 = "";
        enunciadoRespuesta2 = "";
        valorRespuesta2 = "";
        enunciadoRespuesta3 = "";
        valorRespuesta3 = "";
        enunciadoRespuesta4 = "";
        valorRespuesta4 = "";
        llenarNumeroPreguntas();
        llenarVerdaderoOFalso();
        llenarValorCorrecto();
        lstPreguntaVerdaderoOFalsos = new ArrayList<>();
        lstPreguntaOpcionMultiple = new ArrayList<>();
        mostrarTablaPreguntasVerdaderoOFalso = false;
        mostrarBotonAceptar = true;

    }

    private void llenarVerdaderoOFalso() {
        verdaderoOFalso = new ArrayList<>();
        verdaderoOFalso.add("Verdadero");
        verdaderoOFalso.add("Falso");
    }
    
    private void llenarValorCorrecto() {
        valorCorrecto = new ArrayList<>();
        valorCorrecto.add("Correcto");
        valorCorrecto.add("Incorrecto");
    }

    private void llenarNumeroPreguntas() {
        //options
        numeroPreguntas = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            numeroPreguntas.add(String.valueOf(i + 1));
        }
        
        numeroPreguntasMultiple = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            numeroPreguntasMultiple.add(String.valueOf(i + 1));
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
            
            finalizarPreguntasOpcionMultiple(examenAux);

        } catch (Exception e) {
            System.err.println("Error"+e);
            inicializar();
        }

        inicializar();
    }
    
    public void finalizarPreguntasOpcionMultiple(Examen examenAux){
        for(PreguntaOpcionMultiple preguntaOpcionMultiple: lstPreguntaOpcionMultiple){
            Preguntaopcionmultiple preguntaopcionmultipleAux = new Preguntaopcionmultiple();
            preguntaopcionmultipleAux.setIdpreguntam(Long.valueOf(preguntaopcionmultipleFacadeLocal.ultimoRegistro()));
            preguntaopcionmultipleAux.setEnunciado(preguntaOpcionMultiple.getEnunciado());
            preguntaopcionmultipleAux.setExamen(examenAux);
            preguntaopcionmultipleFacadeLocal.insertar(preguntaopcionmultipleAux);
            
            finalizarRespuestassOpcionMultiple(preguntaOpcionMultiple, preguntaopcionmultipleAux);
        }
    }
    
    public void finalizarRespuestassOpcionMultiple(PreguntaOpcionMultiple preguntaOpcionMultiple, Preguntaopcionmultiple preguntaopcionmultipleAux){
     
            Respuestaopcionmultiple respuestaopcionmultipleAux1 = new Respuestaopcionmultiple();
            respuestaopcionmultipleAux1.setEnunciado(preguntaOpcionMultiple.getEnunciadoRespuesta1());
            respuestaopcionmultipleAux1.setRespuesta(preguntaOpcionMultiple.getValorRespuesta1());
            respuestaopcionmultipleAux1.setPreguntaopcionmultiple(preguntaopcionmultipleAux);
            respuestaopcionmultipleFacadeLocal.insertar(respuestaopcionmultipleAux1);
            Respuestaopcionmultiple respuestaopcionmultipleAux2 = new Respuestaopcionmultiple();
            respuestaopcionmultipleAux2.setEnunciado(preguntaOpcionMultiple.getEnunciadoRespuesta2());
            respuestaopcionmultipleAux2.setRespuesta(preguntaOpcionMultiple.getValorRespuesta2());
            respuestaopcionmultipleAux2.setPreguntaopcionmultiple(preguntaopcionmultipleAux);
            respuestaopcionmultipleFacadeLocal.insertar(respuestaopcionmultipleAux2);
            Respuestaopcionmultiple respuestaopcionmultipleAux3 = new Respuestaopcionmultiple();
            respuestaopcionmultipleAux3.setEnunciado(preguntaOpcionMultiple.getEnunciadoRespuesta3());
            respuestaopcionmultipleAux3.setRespuesta(preguntaOpcionMultiple.getValorRespuesta3());
            respuestaopcionmultipleAux3.setPreguntaopcionmultiple(preguntaopcionmultipleAux);
            respuestaopcionmultipleFacadeLocal.insertar(respuestaopcionmultipleAux3);
            Respuestaopcionmultiple respuestaopcionmultipleAux4 = new Respuestaopcionmultiple();
            respuestaopcionmultipleAux4.setEnunciado(preguntaOpcionMultiple.getEnunciadoRespuesta4());
            respuestaopcionmultipleAux4.setRespuesta(preguntaOpcionMultiple.getValorRespuesta4());
            respuestaopcionmultipleAux4.setPreguntaopcionmultiple(preguntaopcionmultipleAux);
            respuestaopcionmultipleFacadeLocal.insertar(respuestaopcionmultipleAux4);
    }
    
    public void mostrarTabla(ActionListener actionListener) {

        llenarLstMaterias();
        llenarListaPreguntas(actionListener);
        llenarListaPreguntasOpcionMultiple(actionListener);
        mostrarBotonAceptar = false;
        mostrarTablaPreguntasVerdaderoOFalso = true;
        System.out.println("MARCA69: " + numeroPreguntasVerdaderoOFalso);
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
    
    public void cambiarNumeroPreguntasOpcionMultiple(ValueChangeEvent event) {
        String numeroPreguntasAux = (String) event.getNewValue();
        numeroPreguntasOpcionMultiple = Integer.valueOf(numeroPreguntasAux);
        System.out.println("MARCA21: " + numeroPreguntasOpcionMultiple);
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
    
    public void llenarListaPreguntasOpcionMultiple(ActionListener actionListener) {

        for (int i = 0; i < numeroPreguntasOpcionMultiple; i++) {
            PreguntaOpcionMultiple preguntaOpcionMultiple = new PreguntaOpcionMultiple();
            preguntaOpcionMultiple.setNumeroPregunta(i + 1);
            preguntaOpcionMultiple.setEnunciado("");
            lstPreguntaOpcionMultiple.add(preguntaOpcionMultiple);
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
        //System.out.println("MARCA 22: " + mostrarTablaPreguntasVerdaderoOFalso);
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

    public PreguntaverdaderofalsoFacadeLocal getPreguntaverdaderofalsoFacadeLocal() {
        return preguntaverdaderofalsoFacadeLocal;
    }

    public void setPreguntaverdaderofalsoFacadeLocal(PreguntaverdaderofalsoFacadeLocal preguntaverdaderofalsoFacadeLocal) {
        this.preguntaverdaderofalsoFacadeLocal = preguntaverdaderofalsoFacadeLocal;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public int getNumeroPreguntasOpcionMultiple() {
        return numeroPreguntasOpcionMultiple;
    }

    public void setNumeroPreguntasOpcionMultiple(int numeroPreguntasOpcionMultiple) {
        this.numeroPreguntasOpcionMultiple = numeroPreguntasOpcionMultiple;
    }

    public List<String> getNumeroPreguntasMultiple() {
        return numeroPreguntasMultiple;
    }

    public void setNumeroPreguntasMultiple(List<String> numeroPreguntasMultiple) {
        this.numeroPreguntasMultiple = numeroPreguntasMultiple;
    }

    public List<PreguntaOpcionMultiple> getLstPreguntaOpcionMultiple() {
        return lstPreguntaOpcionMultiple;
    }

    public void setLstPreguntaOpcionMultiple(List<PreguntaOpcionMultiple> lstPreguntaOpcionMultiple) {
        this.lstPreguntaOpcionMultiple = lstPreguntaOpcionMultiple;
    }

    public String getEnunciadoRespuesta1() {
        return enunciadoRespuesta1;
    }

    public void setEnunciadoRespuesta1(String enunciadoRespuesta1) {
        this.enunciadoRespuesta1 = enunciadoRespuesta1;
    }

    public String getValorRespuesta1() {
        return valorRespuesta1;
    }

    public void setValorRespuesta1(String valorRespuesta1) {
        this.valorRespuesta1 = valorRespuesta1;
    }

    public String getEnunciadoRespuesta2() {
        return enunciadoRespuesta2;
    }

    public void setEnunciadoRespuesta2(String enunciadoRespuesta2) {
        this.enunciadoRespuesta2 = enunciadoRespuesta2;
    }

    public String getValorRespuesta2() {
        return valorRespuesta2;
    }

    public void setValorRespuesta2(String valorRespuesta2) {
        this.valorRespuesta2 = valorRespuesta2;
    }

    public String getEnunciadoRespuesta3() {
        return enunciadoRespuesta3;
    }

    public void setEnunciadoRespuesta3(String enunciadoRespuesta3) {
        this.enunciadoRespuesta3 = enunciadoRespuesta3;
    }

    public String getValorRespuesta3() {
        return valorRespuesta3;
    }

    public void setValorRespuesta3(String valorRespuesta3) {
        this.valorRespuesta3 = valorRespuesta3;
    }

    public String getEnunciadoRespuesta4() {
        return enunciadoRespuesta4;
    }

    public void setEnunciadoRespuesta4(String enunciadoRespuesta4) {
        this.enunciadoRespuesta4 = enunciadoRespuesta4;
    }

    public String getValorRespuesta4() {
        return valorRespuesta4;
    }

    public void setValorRespuesta4(String valorRespuesta4) {
        this.valorRespuesta4 = valorRespuesta4;
    }

    public PreguntaOpcionMultiple getPreguntaOpcionMultiple() {
        return preguntaOpcionMultiple;
    }

    public void setPreguntaOpcionMultiple(PreguntaOpcionMultiple preguntaOpcionMultiple) {
        this.preguntaOpcionMultiple = preguntaOpcionMultiple;
    }

    public List<String> getValorCorrecto() {
        return valorCorrecto;
    }

    public void setValorCorrecto(List<String> valorCorrecto) {
        this.valorCorrecto = valorCorrecto;
    }
    
    

}
