package org.example.servicios;

import org.example.clases.Registro;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.example.clases.Usuario;

import java.util.List;


/**
 *
 * Created by vacax on 03/06/16.
 */
public class RegistroServices extends GestionDb<Registro> {

    private static RegistroServices instancia;

    private RegistroServices() {
        super(Registro.class);
    }

    public static RegistroServices getInstancia(){
        if(instancia==null){
            instancia = new RegistroServices();
        }
        return instancia;
    }

    /**
     *
     * @param nombre
     * @return
     */
    public List<Registro> findAllByNombre(String nombre){
        EntityManager em = getEntityManager();
        Query query = em.createQuery("select e from Registro e where e.nombre like :nombre");
        query.setParameter("nombre", nombre+"%");
        List<Registro> lista = query.getResultList();
        return lista;
    }

    public List<Registro> consultaNativa(){
        EntityManager em = getEntityManager();
        Query query = em.createNativeQuery("select * from Registro ", Registro.class);
        //query.setParameter("nombre", apellido+"%");
        List<Registro> lista = query.getResultList();
        return lista;
    }
/*
    public void pruebaActualizacion(){

        EntityManager em = getEntityManager();
        Registro est = new Registro("José Jaquez", "Los ciruelitos", em.toString(), "Grado", "123", "100",false);
        em.getTransaction().begin();
        em.persist(est); //creando el registro.
        em.flush();
        em.getTransaction().commit(); //persistiendo el registro.
        em.getTransaction().begin();
        est.setNombre("Otro Nombre");
        em.flush();
        em.getTransaction().commit();
        em.getTransaction().begin();
        est.setNombre("Nuevamente otro nombre...");
        em.flush();
        em.getTransaction().commit();
        em.close(); //todos los objetos abiertos y manejados fueron cerrados.
        //
        est.setNombre("Cambiando el objeto..."); //en memoria, no en la base datos.
        em = getEntityManager();
        em.getTransaction().begin();
        em.merge(est);
        em.getTransaction().commit();
        Usuario p = em.find(Usuario.class, 1);
        System.out.println("El nombre del Usuario: "+p.getNombre());
        em.close();
    }

*/
}
