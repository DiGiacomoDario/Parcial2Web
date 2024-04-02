package org.example.servicios;

import org.example.clases.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class UsuarioServices extends GestionDb<Usuario> {

    private static UsuarioServices instancia;

    private UsuarioServices(){
        super(Usuario.class);
    }

    public static UsuarioServices getInstancia(){
        if(instancia==null){
            instancia = new UsuarioServices();
        }
        return instancia;
    }

    public Usuario findByNombre(String nombre){
        EntityManager em = getEntityManager();
        TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u WHERE u.nombre = :nombre", Usuario.class);
        query.setParameter("nombre", nombre);
        return query.getSingleResult();
    }
}