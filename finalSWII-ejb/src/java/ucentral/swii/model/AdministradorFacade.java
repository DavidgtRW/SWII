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
public class AdministradorFacade extends AbstractFacade<Administrador> implements AdministradorFacadeLocal {

    @PersistenceContext(unitName = "finalSWII-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AdministradorFacade() {
        super(Administrador.class);
    }
    
    @Override
    public void insertar(Administrador administrador) {
        Query query = em.createNativeQuery("INSERT INTO administrador(\"idAdministrador\", correo, usuario)VALUES (?, ?, ?)");
        int cont = 1;
        query.setParameter(cont, administrador.getIdAdministrador());
        query.setParameter(cont++, administrador.getCorreo());
        query.setParameter(cont++, administrador.getUsuario().getIdUsuario());
        query.executeUpdate();
    }

    @Override
    public List<Administrador> getAdministradores() {
        Query query = em.createNativeQuery("SELECT \"idAdministrador\", correo, usuario "
                + "FROM administrador", Administrador.class);

        return query.getResultList();
    }
    
}
