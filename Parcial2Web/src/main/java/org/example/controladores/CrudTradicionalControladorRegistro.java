package org.example.controladores;

import org.example.clases.Coordenada;
import org.example.clases.Registro;
import org.example.clases.Usuario;
import org.example.util.BaseControlador;
import io.javalin.Javalin;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.*;
import java.util.stream.Collectors;

import static io.javalin.apibuilder.ApiBuilder.*;

public class CrudTradicionalControladorRegistro extends BaseControlador {
    EntityManager em;

    public CrudTradicionalControladorRegistro(Javalin app, EntityManager em) {
        super(app);
        this.em = em;
    }

    @Override
    public void aplicarRutas() {

        app.routes(() -> {
            path("/crud-simple-registro/", () -> {
                get("/", ctx -> ctx.redirect("/crud-simple-registro/listar"));


                get("/listar", ctx -> {
                    TypedQuery<Registro> query = em.createQuery("SELECT r FROM Registro r", Registro.class);
                    List<Registro> lista = query.getResultList();
                    Map<String, Object> modelo = new HashMap<>();
                    modelo.put("titulo", "Listado de REGISTROS");
                    modelo.put("lista", lista);
                    ctx.render("/templates/crud-tradicional/listarRegistro.html", modelo);
                });

                get("/crear", ctx -> {
                    Map<String, Object> modelo = new HashMap<>();
                    modelo.put("titulo", "FORMULARIO CREACION REGISTRO");
                    modelo.put("accion", "/crud-simple-registro/crear");
                    ctx.render("/templates/crud-tradicional/crearEditarVisualizarRegistro.html", modelo);
                });

                post("/crear", ctx -> {
                    String username = ctx.sessionAttribute("username");
                    if (username == null) {
                        //rellena el campo user con la palabra admin
                        username = "admin";
                    }

                    Usuario usuario = em.find(Usuario.class, username);
                    if (usuario == null) {
                        System.out.println("No se encontró un Usuario con el username: " + username + ". Redirigiendo al inicio.");
                        ctx.redirect("/");
                        return;
                    }

                    String nombre = ctx.formParam("nombre");
                    String sector = ctx.formParam("sector");
                    String nivelEscolar = ctx.formParam("nivelEscolar");
                    String latitud = ctx.formParam("latitud");
                    String longitud = ctx.formParam("longitud");
                    boolean estado = ctx.formParam("estado") != null;

                    Registro registro = new Registro();
                    registro.setNombre(nombre);
                    registro.setSector(sector);
                    registro.setNivelEscolar(nivelEscolar);
                    registro.setUsername(usuario.getUsername());
                    registro.setLatitud(latitud);
                    registro.setLongitud(longitud);
                    registro.setEstado(estado);
                    registro.setUsuario(usuario);

                    em.getTransaction().begin();
                    em.persist(registro);
                    em.getTransaction().commit();

                    ctx.redirect("/crud-simple-registro/");
                });

                get("/visualizar/{id}", ctx -> {
                    Registro registro = em.find(Registro.class, ctx.pathParam("id"));
                    Map<String, Object> modelo = new HashMap<>();
                    modelo.put("titulo", "Formulario Visualizar Registro " + registro.getUsuario());
                    modelo.put("visualizar", true);
                    modelo.put("accion", "/crud-simple-registro/");
                    ctx.render("/templates/crud-tradicional/crearEditarVisualizarRegistro.html", modelo);
                });

                get("/coordenadas", ctx -> {
                    TypedQuery<Registro> query = em.createQuery("SELECT r FROM Registro r", Registro.class);
                    List<Registro> registros = query.getResultList();
                    List<Coordenada> coordenadas = registros.stream()
                            .map(r -> new Coordenada((r.getLatitud()), (r.getLongitud())))
                            .collect(Collectors.toList());
                    ctx.json(coordenadas);
                });

                get("/editar/{id}", ctx -> {
                    Registro registro = em.find(Registro.class, ctx.pathParam("id"));
                    Map<String, Object> modelo = new HashMap<>();
                    modelo.put("titulo", "FORMULARIO EDITAR REGISTRO: " + registro.getUsuario().getNombre());
                    modelo.put("registro", registro);
                    modelo.put("accion", "/crud-simple-registro/editar");
                    ctx.render("/templates/crud-tradicional/crearEditarVisualizarRegistro.html", modelo);
                });

                post("/editar", ctx -> {
                    String id = ctx.formParam("id");
                    Registro registroExistente = em.find(Registro.class, id);

                    if (registroExistente != null) {
                        String nombre = ctx.formParam("nombre");
                        String sector = ctx.formParam("sector");
                        String nivelEscolar = ctx.formParam("nivelEscolar");
                        String latitud = ctx.formParam("latitud");
                        String longitud = ctx.formParam("longitud");
                        boolean estado = ctx.formParam("estado") != null;

                        registroExistente.setNombre(nombre);
                        registroExistente.setSector(sector);
                        registroExistente.setNivelEscolar(nivelEscolar);
                        registroExistente.setLatitud(latitud);
                        registroExistente.setLongitud(longitud);
                        registroExistente.setEstado(estado);

                        em.getTransaction().begin();
                        em.merge(registroExistente);
                        em.getTransaction().commit();
                    } else {
                        System.out.println("No se encontró el registro con el id: " + id);
                    }

                    ctx.redirect("/crud-simple-registro/");
                });

                get("/eliminar/{id}", ctx -> {
                    Registro registro = em.find(Registro.class, ctx.pathParam("id"));
                    if (registro != null) {
                        em.getTransaction().begin();
                        em.remove(registro);
                        em.getTransaction().commit();
                    }
                    ctx.redirect("/crud-simple-registro/");
                });

            });
        });

    }
}
