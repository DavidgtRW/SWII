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
import ucentral.swii.entities.Preguntaopcionmultiple;

/**
 *
 * @author david
 */
@Stateless
public class PreguntaopcionmultipleFacade extends AbstractFacade<Preguntaopcionmultiple> implements PreguntaopcionmultipleFacadeLocal {

    @PersistenceContext(unitName = "finalSWII-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PreguntaopcionmultipleFacade() {
        super(Preguntaopcionmultiple.class);
    }

    @Override
    public void insertar(Preguntaopcionmultiple preguntaopcionmultiple) {
        Query query = em.createNativeQuery("INSERT INTO preguntaopcionmultiple(idpreguntam, enunciado, examen)"
                + " VALUES ("+preguntaopcionmultiple.getIdpreguntam()+", '"+preguntaopcionmultiple.getEnunciado()+"', "+preguntaopcionmultiple.getExamen().getIdexamen()+")");
        query.executeUpdate();
    }

    @Override
    public int ultimoRegistro() {
        Query query = em.createNativeQuery("SELECT max(idpreguntam) FROM preguntaopcionmultiple");
        int valor = 1;
        if(query.getSingleResult()==null){
            return valor;
        }else{
            valor = ((Long) query.getSingleResult()).intValue() + 1;
        }
            
        return valor;
    }
    
}
