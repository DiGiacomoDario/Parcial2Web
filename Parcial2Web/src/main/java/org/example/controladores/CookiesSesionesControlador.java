package org.example.controladores;

import org.example.clases.Usuario;
import org.example.util.BaseControlador;
import io.javalin.Javalin;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;

public class CookiesSesionesControlador extends BaseControlador {
    EntityManager em;
    public static List<String> lista = new ArrayList<>();

    public CookiesSesionesControlador(Javalin app, EntityManager em) {
        super(app);
        this.em = em;
        crearUsuarioAdminPorDefecto();
    }

    @Override
    public void aplicarRutas() {
        app.post("/login", ctx -> {
            String username = ctx.formParam("username");
            String password = ctx.formParam("password");
            TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u WHERE u.username = :username AND u.password = :password", Usuario.class);
            query.setParameter("username", username);
            query.setParameter("password", password);
            Usuario usuario = query.getSingleResult();
        });
        app.get("/logout", ctx -> {
            ctx.req().getSession().invalidate();
            ctx.redirect("/login");
        });
    }

    public void crearUsuarioAdminPorDefecto() {
    Usuario admin = new Usuario();
    admin.setNombre("admin");
    admin.setUsername("admin");
    admin.setPassword("123");
    admin.setAdmintrator(true);
    admin.setUsuario(false);

    TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u WHERE u.username = :username", Usuario.class);
    query.setParameter("username", admin.getUsername());
    List<Usuario> usuarios = query.getResultList();

    if (usuarios.isEmpty()) { // Si el usuario no existe, entonces lo creamos
        em.getTransaction().begin();
        em.persist(admin);
        em.getTransaction().commit();
    }
}

    public void crearUsuarioUsuarioPorDefecto() {
        Usuario usuario = new Usuario();
        usuario.setNombre("usuario");
        usuario.setUsername("usuario");
        usuario.setPassword("usuario");
        usuario.setAdmintrator(false);
        usuario.setUsuario(true);
        em.getTransaction().begin();
        em.persist(usuario);
        em.getTransaction().commit();
    }
}