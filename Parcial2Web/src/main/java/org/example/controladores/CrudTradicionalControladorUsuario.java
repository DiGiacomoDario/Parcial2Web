package org.example.controladores;

import org.example.clases.Usuario;
import org.example.util.BaseControlador;
import io.javalin.Javalin;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.*;
import static io.javalin.apibuilder.ApiBuilder.*;

public class CrudTradicionalControladorUsuario extends BaseControlador {
    EntityManagerFactory emf;
    EntityManager em;

    public CrudTradicionalControladorUsuario(Javalin app, EntityManager em) {
        super(app);
        this.em = em;
    }

    @Override
    public void aplicarRutas() {
        app.routes(() -> {
            path("/crud-simple-usuario/", () -> {

                get("/", ctx -> {
                    ctx.redirect("/crud-simple-usuario/listar");
                });

                get("/listar", ctx -> {
                    List<Usuario> lista = em.createQuery("SELECT u FROM Usuario u", Usuario.class).getResultList();
                    Map<String, Object> modelo = new HashMap<>();
                    modelo.put("titulo", "Listado de USUARIOS");
                    modelo.put("lista", lista);
                    ctx.render("/templates/crud-tradicional/listarUsuario.html", modelo);
                });

                get("/crear", ctx -> {
                    Map<String, Object> modelo = new HashMap<>();
                    modelo.put("titulo", "FORMULARIO CREACION USUARIO");
                    modelo.put("accion", "/crud-simple-usuario/crear");
                    ctx.render("/templates/crud-tradicional/crearEditarVisualizarUsuario.html", modelo);
                });

                post("/crear", ctx -> {
                    String username = ctx.formParam("username");
                    String nombre = ctx.formParam("nombre");
                    String password = ctx.formParam("password");

                    boolean admintrator = ctx.formParam("admintrator") != null;
                    boolean usuario = ctx.formParam("usuario") != null;

                    Usuario usuarios = new Usuario(username, nombre, password, admintrator, usuario);
                    em.getTransaction().begin();
                    em.persist(usuarios);
                    em.getTransaction().commit();
                    ctx.redirect("/crud-simple-usuario/");
                });

                get("/crearLogin", ctx -> {
                    Map<String, Object> modelo = new HashMap<>();
                    modelo.put("titulo", "FORMULARIO CREACION USUARIO");
                    modelo.put("accion", "/crud-simple-usuario/crearLogin");
                    ctx.render("/templates/crud-tradicional/crearEditarVisualizarUsuario.html", modelo);
                });

                post("/crearLogin", ctx -> {
                    String username = ctx.formParam("username");
                    String nombre = ctx.formParam("nombre");
                    String password = ctx.formParam("password");

                    boolean admintrator = ctx.formParam("admintrator") != null;
                    boolean usuario = ctx.formParam("usuario") != null;

                    Usuario usuarios = new Usuario(username, nombre, password, admintrator, usuario);
                    em.getTransaction().begin();
                    em.persist(usuarios);
                    em.getTransaction().commit();
                    ctx.redirect("/");
                });

                get("/visualizar/{username}", ctx -> {
                    Usuario usuario = em.find(Usuario.class, ctx.pathParam("username"));
                    Map<String, Object> modelo = new HashMap<>();
                    modelo.put("titulo", "Formulario Visualizar Usuario " + usuario.getUsername());
                    modelo.put("usuario", usuario);
                    modelo.put("visualizar", true);
                    modelo.put("accion", "/crud-simple-usuario/");
                    ctx.render("/templates/crud-tradicional/crearEditarVisualizarUsuario.html", modelo);
                });

                get("/editar/{username}", ctx -> {
                    Usuario usuario = em.find(Usuario.class, ctx.pathParam("username"));
                    Map<String, Object> modelo = new HashMap<>();
                    modelo.put("titulo", "Formulario Editar Usuario " + usuario.getUsername());
                    modelo.put("usuario", usuario);
                    modelo.put("accion", "/crud-simple-usuario/editar");
                    ctx.render("/templates/crud-tradicional/crearEditarVisualizarUsuario.html", modelo);
                });

                post("/editar", ctx -> {
                    String username = ctx.formParam("username");
                    String nombre = ctx.formParam("nombre");
                    String password = ctx.formParam("password");

                    boolean admintrator = ctx.formParam("admintrator") != null;
                    boolean usuario = ctx.formParam("usuario") != null;
                    Usuario usuarioExistente = em.find(Usuario.class, username);

                    if (usuarioExistente != null) {
                        usuarioExistente.setNombre(nombre);
                        usuarioExistente.setPassword(password);
                        usuarioExistente.setAdmintrator(admintrator);
                        usuarioExistente.setUsuario(usuario);
                        em.getTransaction().begin();
                        em.merge(usuarioExistente);
                        em.getTransaction().commit();
                    } else {
                        System.out.println("No se encontró el usuario con el username: " + username);
                    }

                    ctx.redirect("/crud-simple-usuario/");
                });

                get("/eliminar/{username}", ctx -> {
                    Usuario usuario = em.find(Usuario.class, ctx.pathParam("username"));
                    if (usuario != null) {
                        em.getTransaction().begin();
                        em.remove(usuario);
                        em.getTransaction().commit();
                    }
                    ctx.redirect("/crud-simple-usuario/");
                });

            });

        });
    }
}