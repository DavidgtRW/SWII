/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ucentral.swii.model;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import ucentral.swii.entities.Profesor;

/**
 *
 * @author david
 */
@Stateless
public class ProfesorFacade implements ProfesorFacadeLocal {

    @PersistenceContext(unitName = "finalSWII-ejbPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public ProfesorFacade() {
        
    }

    @Override
    public void remove(Profesor profesor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int count() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insertar(Profesor profesor) {
        Query query = em.createNativeQuery("INSERT INTO profesor(\"idProfesor\", nombre, apellido, correo, telefono, usuario) "
                + "VALUES ("+ ultimoRegistro() +", '"+ profesor.getNombre()+"', "
                        + "'"+profesor.getApellido()+"', '"+profesor.getCorreo()+"', '"+profesor.getTelefono()+"'"
                        + ", "+ profesor.getUsuario().getIdUsuario()+")");
        /*
        int cont = 1;
        query.setParameter(cont, profesor.getNombre());
        query.setParameter(cont++, profesor.getApellido());
        query.setParameter(cont++, profesor.getCorreo());
        query.setParameter(cont++, profesor.getTelefono());
        */
        
        System.out.println("**************MARCA 11: "+profesor.getNombre());
        System.out.println("**************MARCA 12: "+query);
        query.executeUpdate();
    }
    
    private int ultimoRegistro() {
        Query query = em.createNativeQuery("SELECT max(\"idProfesor\") FROM profesor");
        int valor = 1;
        if(query.getSingleResult()==null){
            return valor;
        }else{
            valor = ((Long) query.getSingleResult()).intValue() + 1;
        }
            
        return valor;
    }

    @Override
    public List<Profesor> getAdministradores() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long encontrarUsuario(Profesor profesor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  
    
}
