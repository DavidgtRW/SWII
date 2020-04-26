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
import ucentral.swii.entities.Usuario;

/**
 *
 * @author david
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> implements UsuarioFacadeLocal {

    @PersistenceContext(unitName = "finalSWII-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }

    @Override
    public void insertar(Usuario usuario) {
        Query query = em.createNativeQuery("INSERT INTO usuario (\"idUsuario\", \"nombreUsuario\", contrasena, \"tipoUsuario\", estado) "
                + "VALUES (" + ultimoRegistro() + ", '" + usuario.getNombreUsuario() + "', '" + usuario.getContrasena() + "', "
                + "'" + usuario.getTipoUsuario() + "', '" + usuario.getEstado() + "')");
        query.executeUpdate();
    }

    @Override
    public List<Usuario> getUsuarios() {
        Query query = em.createNativeQuery("SELECT u.\"idUsuario\", u.\"nombreUsuario\", u.contrasena, u.\"tipoUsuario\", u.estado "
                + "FROM usuario u", Usuario.class);

        return query.getResultList();
    }

    private int ultimoRegistro() {
        Query query = em.createNativeQuery("select count(*) from usuario");
        return ((Long) query.getSingleResult()).intValue() + 1;

    }

    @Override
    public Usuario findByCredenciales(String usuario, String contrasena) {
        String sql = "SELECT * FROM public.usuario u  WHERE u.\"nombreUsuario\" = \'"+ usuario +"\' AND  u.\"contrasena\" = \'"+ contrasena +"\'";
        Query query = em.createNativeQuery(sql,Usuario.class);   
        List<Usuario> usuarios = query.getResultList();
        return usuarios.get(0);

    }

}
