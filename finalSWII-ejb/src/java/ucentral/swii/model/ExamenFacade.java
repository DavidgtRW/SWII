/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ucentral.swii.model;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import ucentral.swii.entities.Examen;

/**
 *
 * @author david
 */
@Stateless
public class ExamenFacade extends AbstractFacade<Examen> implements ExamenFacadeLocal {

    @PersistenceContext(unitName = "finalSWII-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ExamenFacade() {
        super(Examen.class);
    }

    @Override
    public void insertar(Examen examen) {
        Query query = em.createNativeQuery("INSERT INTO examen (idexamen, materia, nombre) "
                + "VALUES ("+examen.getIdexamen()+", "+examen.getMateria().getIdmateria()+", '"+examen.getNombre()+"') ");
        query.executeUpdate();
    }
    
    @Override
    public int ultimoRegistro() {
        Query query = em.createNativeQuery("SELECT max(idexamen) FROM examen");
        int valor = 1;
        if(query.getSingleResult()==null){
            return valor;
        }else{
            valor = ((Long) query.getSingleResult()).intValue() + 1;
        }
            
        return valor;
    }
}
