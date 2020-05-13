/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ucentral.swii.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author david
 */
@Entity
@Table(name = "respuestaopcionmultiple")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Respuestaopcionmultiple.findAll", query = "SELECT r FROM Respuestaopcionmultiple r")
    , @NamedQuery(name = "Respuestaopcionmultiple.findByIdrespuestam", query = "SELECT r FROM Respuestaopcionmultiple r WHERE r.idrespuestam = :idrespuestam")
    , @NamedQuery(name = "Respuestaopcionmultiple.findByEnunciado", query = "SELECT r FROM Respuestaopcionmultiple r WHERE r.enunciado = :enunciado")
    , @NamedQuery(name = "Respuestaopcionmultiple.findByRespuesta", query = "SELECT r FROM Respuestaopcionmultiple r WHERE r.respuesta = :respuesta")})
public class Respuestaopcionmultiple implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idrespuestam")
    private Long idrespuestam;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "enunciado")
    private String enunciado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "respuesta")
    private String respuesta;
    @JoinColumn(name = "preguntaopcionmultiple", referencedColumnName = "idpreguntam")
    @ManyToOne(optional = false)
    private Preguntaopcionmultiple preguntaopcionmultiple;

    public Respuestaopcionmultiple() {
    }

    public Respuestaopcionmultiple(Long idrespuestam) {
        this.idrespuestam = idrespuestam;
    }

    public Respuestaopcionmultiple(Long idrespuestam, String enunciado, String respuesta) {
        this.idrespuestam = idrespuestam;
        this.enunciado = enunciado;
        this.respuesta = respuesta;
    }

    public Long getIdrespuestam() {
        return idrespuestam;
    }

    public void setIdrespuestam(Long idrespuestam) {
        this.idrespuestam = idrespuestam;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public Preguntaopcionmultiple getPreguntaopcionmultiple() {
        return preguntaopcionmultiple;
    }

    public void setPreguntaopcionmultiple(Preguntaopcionmultiple preguntaopcionmultiple) {
        this.preguntaopcionmultiple = preguntaopcionmultiple;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idrespuestam != null ? idrespuestam.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Respuestaopcionmultiple)) {
            return false;
        }
        Respuestaopcionmultiple other = (Respuestaopcionmultiple) object;
        if ((this.idrespuestam == null && other.idrespuestam != null) || (this.idrespuestam != null && !this.idrespuestam.equals(other.idrespuestam))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ucentral.swii.entities.Respuestaopcionmultiple[ idrespuestam=" + idrespuestam + " ]";
    }
    
}
