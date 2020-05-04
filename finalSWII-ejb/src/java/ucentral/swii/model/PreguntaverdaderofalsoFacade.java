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
import ucentral.swii.entities.Preguntaverdaderofalso;

/**
 *
 * @author david
 */
@Stateless
public class PreguntaverdaderofalsoFacade extends AbstractFacade<Preguntaverdaderofalso> implements PreguntaverdaderofalsoFacadeLocal {

    @PersistenceContext(unitName = "finalSWII-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PreguntaverdaderofalsoFacade() {
        super(Preguntaverdaderofalso.class);
    }

    @Override
    public void insertar(Preguntaverdaderofalso preguntaverdaderofalso) {
        Query query = em.createNativeQuery("INSERT INTO preguntaverdaderofalso(idpregunta, examen, enunciado, respuesta) "
                + "VALUES ("+ultimoRegistro()+", "+preguntaverdaderofalso.getExamen().getIdexamen()+", '"+preguntaverdaderofalso.getEnunciado()+"',"
                        + " '"+preguntaverdaderofalso.getRespuesta()+"') ");
        query.executeUpdate();
    }
    
    private int ultimoRegistro() {
        Query query = em.createNativeQuery("SELECT max(idpregunta) FROM preguntaverdaderofalso");
        int valor = 1;
        if(query.getSingleResult()==null){
            return valor;
        }else{
            valor = ((Long) query.getSingleResult()).intValue() + 1;
        }
            
        return valor;
    }
    
}
