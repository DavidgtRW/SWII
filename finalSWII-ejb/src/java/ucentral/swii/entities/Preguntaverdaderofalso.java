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
@Table(name = "preguntaverdaderofalso")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Preguntaverdaderofalso.findAll", query = "SELECT p FROM Preguntaverdaderofalso p")
    , @NamedQuery(name = "Preguntaverdaderofalso.findByIdpregunta", query = "SELECT p FROM Preguntaverdaderofalso p WHERE p.idpregunta = :idpregunta")
    , @NamedQuery(name = "Preguntaverdaderofalso.findByEnunciado", query = "SELECT p FROM Preguntaverdaderofalso p WHERE p.enunciado = :enunciado")
    , @NamedQuery(name = "Preguntaverdaderofalso.findByRespuesta", query = "SELECT p FROM Preguntaverdaderofalso p WHERE p.respuesta = :respuesta")})
public class Preguntaverdaderofalso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idpregunta")
    private Long idpregunta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "enunciado")
    private String enunciado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "respuesta")
    private String respuesta;
    @JoinColumn(name = "examen", referencedColumnName = "idexamen")
    @ManyToOne(optional = false)
    private Examen examen;

    public Preguntaverdaderofalso() {
    }

    public Preguntaverdaderofalso(Long idpregunta) {
        this.idpregunta = idpregunta;
    }

    public Preguntaverdaderofalso(Long idpregunta, String enunciado, String respuesta) {
        this.idpregunta = idpregunta;
        this.enunciado = enunciado;
        this.respuesta = respuesta;
    }

    public Long getIdpregunta() {
        return idpregunta;
    }

    public void setIdpregunta(Long idpregunta) {
        this.idpregunta = idpregunta;
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

    public Examen getExamen() {
        return examen;
    }

    public void setExamen(Examen examen) {
        this.examen = examen;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpregunta != null ? idpregunta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Preguntaverdaderofalso)) {
            return false;
        }
        Preguntaverdaderofalso other = (Preguntaverdaderofalso) object;
        if ((this.idpregunta == null && other.idpregunta != null) || (this.idpregunta != null && !this.idpregunta.equals(other.idpregunta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ucentral.swii.entities.Preguntaverdaderofalso[ idpregunta=" + idpregunta + " ]";
    }
    
}
