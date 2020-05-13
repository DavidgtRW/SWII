/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ucentral.swii.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author david
 */
@Entity
@Table(name = "preguntaopcionmultiple")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Preguntaopcionmultiple.findAll", query = "SELECT p FROM Preguntaopcionmultiple p")
    , @NamedQuery(name = "Preguntaopcionmultiple.findByIdpreguntam", query = "SELECT p FROM Preguntaopcionmultiple p WHERE p.idpreguntam = :idpreguntam")
    , @NamedQuery(name = "Preguntaopcionmultiple.findByEnunciado", query = "SELECT p FROM Preguntaopcionmultiple p WHERE p.enunciado = :enunciado")})
public class Preguntaopcionmultiple implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idpreguntam")
    private Long idpreguntam;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "enunciado")
    private String enunciado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "preguntaopcionmultiple")
    private List<Respuestaopcionmultiple> respuestaopcionmultipleList;
    @JoinColumn(name = "examen", referencedColumnName = "idexamen")
    @ManyToOne(optional = false)
    private Examen examen;

    public Preguntaopcionmultiple() {
    }

    public Preguntaopcionmultiple(Long idpreguntam) {
        this.idpreguntam = idpreguntam;
    }

    public Preguntaopcionmultiple(Long idpreguntam, String enunciado) {
        this.idpreguntam = idpreguntam;
        this.enunciado = enunciado;
    }

    public Long getIdpreguntam() {
        return idpreguntam;
    }

    public void setIdpreguntam(Long idpreguntam) {
        this.idpreguntam = idpreguntam;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    @XmlTransient
    public List<Respuestaopcionmultiple> getRespuestaopcionmultipleList() {
        return respuestaopcionmultipleList;
    }

    public void setRespuestaopcionmultipleList(List<Respuestaopcionmultiple> respuestaopcionmultipleList) {
        this.respuestaopcionmultipleList = respuestaopcionmultipleList;
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
        hash += (idpreguntam != null ? idpreguntam.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Preguntaopcionmultiple)) {
            return false;
        }
        Preguntaopcionmultiple other = (Preguntaopcionmultiple) object;
        if ((this.idpreguntam == null && other.idpreguntam != null) || (this.idpreguntam != null && !this.idpreguntam.equals(other.idpreguntam))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ucentral.swii.entities.Preguntaopcionmultiple[ idpreguntam=" + idpreguntam + " ]";
    }
    
}
