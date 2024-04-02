package org.example;

import io.javalin.Javalin;

import java.util.*;

import io.javalin.http.staticfiles.Location;

import io.javalin.security.RouteRole;
import org.example.clases.Registro;
import org.example.clases.Usuario;
import org.example.controladores.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.*;
import org.example.servicios.FakeServices;


public class Main {

    private static String modoConexion = "";

    enum Rules implements RouteRole {
        ANONYMOUS,
        USER,
    }

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("parcial2");
        EntityManager em = emf.createEntityManager();

        Javalin app = Javalin.create(config -> {
            config.staticFiles.add(staticFileConfig -> {
                staticFileConfig.hostedPath = "/";
                staticFileConfig.directory = "/publico";
                staticFileConfig.location = Location.CLASSPATH;
            });
        });
        app.start(getHerokuAssignedPort());


        app.get("/", ctx -> ctx.render("publico/login.html"));


        app.get("/inicio", ctx -> {



            Map<String, Object> modelo = new HashMap<>();

            modelo.put("usuarioLogueado", ctx.sessionAttribute("usuario"));



            ctx.render("/publico/inicio.html", modelo);


        });

        app.post("/autenticar", ctx -> {
            String nombreUsuario = ctx.formParam("usuario");
            String password = ctx.formParam("password");
            TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u WHERE u.username = :username AND u.password = :password", Usuario.class);
            query.setParameter("username", nombreUsuario);
            query.setParameter("password", password);
            Usuario usuario = query.getSingleResult();
            if (usuario != null) {
                ctx.sessionAttribute("usuario", usuario);
                System.out.println("\n\n\n\n\n\nUsuario autenticado: " + usuario);
                ctx.redirect("/inicio"); // Redirigir al usuario a la página de inicio
            } else {
                ctx.redirect("/");
            }
        });

        app.get("/visualizar/{id}", ctx -> {
            String id = ctx.pathParam("id");
            TypedQuery<Registro> query = em.createQuery("SELECT r FROM Registro r WHERE r.id = :id", Registro.class);
            query.setParameter("id", id);
            Registro registro = query.getSingleResult();

            Usuario usuarioLogueado = ctx.sessionAttribute("usuario");
            if (usuarioLogueado != null) {
                System.out.println("\n\n\n\n\n\nUsuario autenticado: " + usuarioLogueado.getUsername());
            } else {
                System.out.println("\n\n\nNo hay usuario autenticado\n\n\n");
            }

            Map<String, Object> modelo = new HashMap<>();
            modelo.put("usuarioLogueado", usuarioLogueado);
            modelo.put("registro", registro);
            modelo.put("visualizar", true);

            ctx.render("/publico/vistaArticulo.html", modelo);
        });

        app.get("/eliminar/{id}", ctx -> {
            String id = ctx.pathParam("id");
            em.getTransaction().begin();
            Registro registro = em.find(Registro.class, id);
            if (registro != null) {
                em.remove(registro);
            }
            em.getTransaction().commit();
            ctx.redirect("/inicio/");
        });

        //new RecibirDatosControlador(app).aplicarRutas();
        new CookiesSesionesControlador(app, em).aplicarRutas();
        //new ApiControlador(app).aplicarRutas();

        new CrudTradicionalControladorRegistro(app, em).aplicarRutas();
        new CrudTradicionalControladorUsuario(app, em).aplicarRutas();
    }


    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 7000;
    }

    /**
     * Nos
     * @return
     */
    public static String getModoConexion(){
        return modoConexion;
    }




}
