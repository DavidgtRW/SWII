/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ucentral.swii.manejodatoscrearexamen;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author david
 */

public class PreguntaOpcionMultiple {
    
    private int numeroPregunta;
    private String enunciado;
    private String enunciadoRespuesta1;
    private String valorRespuesta1;
    private String enunciadoRespuesta2;
    private String valorRespuesta2;
    private String enunciadoRespuesta3;
    private String valorRespuesta3;
    private String enunciadoRespuesta4;
    private String valorRespuesta4;
    private List<Respuesta> respuestas;

    public PreguntaOpcionMultiple() {
        this.respuestas = new ArrayList<> ();
        
        for(int i = 0; i<4;i++){
            Respuesta respuesta = new Respuesta();
            respuestas.add(respuesta);
        }
        
       
    }

    public int getNumeroPregunta() {
        return numeroPregunta;
    }

    public void setNumeroPregunta(int numeroPregunta) {
        this.numeroPregunta = numeroPregunta;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public List<Respuesta> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(List<Respuesta> respuestas) {
        this.respuestas = respuestas;
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
    
    
}
