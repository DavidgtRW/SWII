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
@Table(name = "examen")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Examen.findAll", query = "SELECT e FROM Examen e")
    , @NamedQuery(name = "Examen.findByIdexamen", query = "SELECT e FROM Examen e WHERE e.idexamen = :idexamen")
    , @NamedQuery(name = "Examen.findByNombre", query = "SELECT e FROM Examen e WHERE e.nombre = :nombre")})
public class Examen implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idexamen")
    private Long idexamen;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "examen")
    private List<Preguntaverdaderofalso> preguntaverdaderofalsoList;
    @JoinColumn(name = "materia", referencedColumnName = "idmateria")
    @ManyToOne(optional = false)
    private Materia materia;

    public Examen() {
    }

    public Examen(Long idexamen) {
        this.idexamen = idexamen;
    }

    public Examen(Long idexamen, String nombre) {
        this.idexamen = idexamen;
        this.nombre = nombre;
    }

    public Long getIdexamen() {
        return idexamen;
    }

    public void setIdexamen(Long idexamen) {
        this.idexamen = idexamen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public List<Preguntaverdaderofalso> getPreguntaverdaderofalsoList() {
        return preguntaverdaderofalsoList;
    }

    public void setPreguntaverdaderofalsoList(List<Preguntaverdaderofalso> preguntaverdaderofalsoList) {
        this.preguntaverdaderofalsoList = preguntaverdaderofalsoList;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idexamen != null ? idexamen.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Examen)) {
            return false;
        }
        Examen other = (Examen) object;
        if ((this.idexamen == null && other.idexamen != null) || (this.idexamen != null && !this.idexamen.equals(other.idexamen))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ucentral.swii.entities.Examen[ idexamen=" + idexamen + " ]";
    }
    
}
