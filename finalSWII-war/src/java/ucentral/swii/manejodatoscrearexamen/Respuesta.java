/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ucentral.swii.manejodatoscrearexamen;

/**
 *
 * @author david
 */
public class Respuesta {
    
    private String enunciadoRespuesta;
    private String correcto;

    public Respuesta() {
    }
    
    public Respuesta(String enunciadoRespuesta, String correcto) {
        this.enunciadoRespuesta = enunciadoRespuesta;
        this.correcto = correcto;
    }

    public String getEnunciadoRespuesta() {
        return enunciadoRespuesta;
    }

    public void setEnunciadoRespuesta(String enunciadoRespuesta) {
        this.enunciadoRespuesta = enunciadoRespuesta;
    }

    public String getCorrecto() {
        return correcto;
    }

    public void setCorrecto(String correcto) {
        this.correcto = correcto;
    }    
}
