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
public class UsuarioFacade implements UsuarioFacadeLocal {

    @PersistenceContext(unitName = "finalSWII-ejbPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
 
    }

    @Override
    public void insertar(Usuario usuario) {
        Query query = em.createNativeQuery("INSERT INTO usuario (\"idUsuario\", \"nombreUsuario\", contrasena, \"tipoUsuario\", estado) "
                + "VALUES (" + usuario.getIdUsuario() + ", '" + usuario.getNombreUsuario() + "', '" + usuario.getContrasena() + "', "
                + "'" + usuario.getTipoUsuario() + "', '" + usuario.getEstado() + "')");
        query.executeUpdate();
    }

    @Override
    public List<Usuario> getUsuarios() {
        Query query = em.createNativeQuery("SELECT u.\"idUsuario\", u.\"nombreUsuario\", u.contrasena, u.\"tipoUsuario\", u.estado "
                + "FROM usuario u", Usuario.class);

        return query.getResultList();
    }

    @Override
    public int ultimoRegistro() {
        Query query = em.createNativeQuery("SELECT max(\"idUsuario\") FROM usuario");
        int valor = 1;
        if(query.getSingleResult()==null){
            return valor;
        }else{
            valor = ((Long) query.getSingleResult()).intValue() + 1;
        }
            
        return valor;

    }

    @Override
    public Usuario findByCredenciales(String usuario, String contrasena) {
        String sql = "SELECT * FROM public.usuario u  WHERE u.\"nombreUsuario\" = \'"+ usuario +"\' AND  u.\"contrasena\" = \'"+ contrasena +"\'";
        Query query = em.createNativeQuery(sql,Usuario.class);   
        List<Usuario> usuarios = query.getResultList();
        return usuarios.get(0);

    }

    @Override
    public int count() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Usuario find(long idUsuario) {
        String sql = ("SELECT \"idUsuario\", contrasena, estado, \"nombreUsuario\", \"tipoUsuario\" FROM usuario WHERE "
                + "(\"idUsuario\" ="+idUsuario+")");
        Query query = em.createNativeQuery(sql,Usuario.class); 
        return (Usuario) query.getSingleResult();
    }

    @Override
    public void remove(Usuario usuario) {
        String sql = ("DELETE FROM usuario WHERE "
                + "(\"idUsuario\" ="+usuario.getIdUsuario()+")");
        Query query = em.createNativeQuery(sql); 
        query.executeUpdate();
    }

}
