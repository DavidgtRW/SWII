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
        String sql = ("DELETE FROM profesor WHERE "
                + "(\"idProfesor\" =" + profesor.getIdProfesor() + ")");
        Query query = em.createNativeQuery(sql);
        System.out.println("*************MARCA7: "+sql);
        query.executeUpdate();
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
    public List<Profesor> getProfesores() {
        Query query = em.createNativeQuery("SELECT \"idProfesor\", nombre, apellido, correo, telefono "
                + "FROM profesor", Profesor.class);

        return query.getResultList();
    }

    @Override
    public long encontrarUsuario(Profesor profesor) {
        Query query = em.createNativeQuery("SELECT usuario FROM profesor "
                + "WHERE (\"idProfesor\" = " + profesor.getIdProfesor()+ ")");
        return ((Long) query.getSingleResult()).intValue();
    }
}
