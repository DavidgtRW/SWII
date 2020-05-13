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
import ucentral.swii.entities.Respuestaopcionmultiple;

/**
 *
 * @author david
 */
@Stateless
public class RespuestaopcionmultipleFacade extends AbstractFacade<Respuestaopcionmultiple> implements RespuestaopcionmultipleFacadeLocal {

    @PersistenceContext(unitName = "finalSWII-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RespuestaopcionmultipleFacade() {
        super(Respuestaopcionmultiple.class);
    }

    @Override
    public void insertar(Respuestaopcionmultiple respuestaopcionmultiple) {
        Query query = em.createNativeQuery("INSERT INTO respuestaopcionmultiple(idrespuestam, enunciado, respuesta, preguntaopcionmultiple)"
                + " VALUES ("+ultimoRegistro()+", '"+respuestaopcionmultiple.getEnunciado()+"', '"+respuestaopcionmultiple.getRespuesta()+"',"
                        + ""+respuestaopcionmultiple.getPreguntaopcionmultiple().getIdpreguntam()+")");
        query.executeUpdate();
    }

    @Override
    public int ultimoRegistro() {
        Query query = em.createNativeQuery("SELECT max(idrespuestam) FROM respuestaopcionmultiple");
        int valor = 1;
        if(query.getSingleResult()==null){
            return valor;
        }else{
            valor = ((Long) query.getSingleResult()).intValue() + 1;
        }
            
        return valor;
    }
    
}
