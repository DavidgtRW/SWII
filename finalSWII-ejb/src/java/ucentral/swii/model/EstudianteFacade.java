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
import ucentral.swii.entities.Estudiante;
import ucentral.swii.entities.Usuario;

/**
 *
 * @author david
 */
@Stateless
public class EstudianteFacade implements EstudianteFacadeLocal {

    @PersistenceContext(unitName = "finalSWII-ejbPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public EstudianteFacade() {

    }

    @Override
    public int count() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove(Estudiante estudiante) {
        String sql = ("DELETE FROM estudiante WHERE "
                + "(\"idEstudiante\" =" + estudiante.getIdEstudiante() + ")");
        Query query = em.createNativeQuery(sql);
        System.out.println("*************MARCA7: " + sql);
        query.executeUpdate();
    }

    @Override
    public void insertar(Estudiante estudiante) {
        Query query = em.createNativeQuery("INSERT INTO public.estudiante(\"idEstudiante\", nombre, apellido, correo, telefono, \"primerIngreso\", usuario) "
                + "VALUES (" + ultimoRegistro() + ", '" + estudiante.getNombre() + "', "
                + "'" + estudiante.getApellido() + "', '" + estudiante.getCorreo() + "', '" + estudiante.getTelefono() + "',"
                + estudiante.getPrimerIngreso() + ", " + estudiante.getUsuario().getIdUsuario() + ")");
        query.executeUpdate();
    }

    @Override
    public List<Estudiante> getEstudiantes() {
        Query query = em.createNativeQuery("SELECT \"idEstudiante\", nombre, apellido, correo, telefono, \"primerIngreso\" "
                + "FROM estudiante", Estudiante.class);

        return query.getResultList();
    }

    @Override
    public long encontrarUsuario(Estudiante estudiante) {
        Query query = em.createNativeQuery("SELECT usuario FROM estudiante "
                + "WHERE (\"idEstudiante\" = " + estudiante.getIdEstudiante() + ")");
        return ((Long) query.getSingleResult()).intValue();
    }

    @Override
    public Estudiante encontrarEstudiante(Usuario usuario) {
        Query query = em.createNativeQuery("SELECT \"idEstudiante\", nombre, apellido, correo, telefono, \"primerIngreso\" FROM estudiante "
                + "WHERE (\"idEstudiante\" = " + usuario.getIdUsuario() + ")", Estudiante.class);
        List<Estudiante> estudiantes = query.getResultList();
        return estudiantes.get(0);
    }

    @Override
    public void updateEstudianteContrasena(String contrasena, Long id) {
        Query query = em.createNativeQuery("UPDATE USUARIO SET CONTRASENA = '"+contrasena+"' WHERE \"idUsuario\" = " + id);
        query.executeUpdate();
    }
    
        @Override
    public void updateEstudianteIngreso(boolean ingreso, Long id) {
        Query query = em.createNativeQuery("UPDATE estudiante SET \"primerIngreso\" = '"+ingreso+"' WHERE \"idEstudiante\" = " + id);
        query.executeUpdate();
    }

    private int ultimoRegistro() {
        Query query = em.createNativeQuery("SELECT max(\"idEstudiante\") FROM estudiante");
        int valor = 1;
        if (query.getSingleResult() == null) {
            return valor;
        } else {
            valor = ((Long) query.getSingleResult()).intValue() + 1;
        }

        return valor;
    }
}
