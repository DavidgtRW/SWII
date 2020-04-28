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
import ucentral.swii.entities.Administrador;

/**
 *
 * @author david
 */
@Stateless
public class AdministradorFacade implements AdministradorFacadeLocal {

    @PersistenceContext(unitName = "finalSWII-ejbPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public AdministradorFacade() {

    }

    @Override
    public void insertar(Administrador administrador) {
        Query query = em.createNativeQuery("INSERT INTO administrador(\"idAdministrador\", correo, usuario) "
                + "VALUES (" + ultimoRegistro() + ", ?, " + administrador.getUsuario().getIdUsuario() + ")");
        int cont = 1;
        query.setParameter(cont, administrador.getCorreo());
        query.executeUpdate();
    }

    private int ultimoRegistro() {
        Query query = em.createNativeQuery("SELECT max(\"idAdministrador\") FROM administrador");
        int valor = 1;
        if(query.getSingleResult()==null){
            return valor;
        }else{
            valor = ((Long) query.getSingleResult()).intValue() + 1;
        }
            
        return valor;
    }

    @Override
    public List<Administrador> getAdministradores() {
        Query query = em.createNativeQuery("SELECT a.\"idAdministrador\", a.correo "
                + "FROM administrador a", Administrador.class);

        return query.getResultList();
    }

    @Override
    public int count() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long encontrarUsuario(Administrador administrador) {
        Query query = em.createNativeQuery("SELECT usuario FROM administrador "
                + "WHERE (\"idAdministrador\" = " + administrador.getIdAdministrador() + ")");
        return ((Long) query.getSingleResult()).intValue();
    }

    @Override
    public void remove(Administrador administrador) {
        String sql = ("DELETE FROM administrador WHERE "
                + "(\"idAdministrador\" =" + administrador.getIdAdministrador() + ")");
        Query query = em.createNativeQuery(sql);
        System.out.println("*************MARCA7: "+sql);
        query.executeUpdate();
    }

}
