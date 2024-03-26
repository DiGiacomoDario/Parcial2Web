package org.example;
import io.javalin.Javalin;

import java.util.*;

import io.javalin.http.staticfiles.Location;

import org.example.clases.Registro;
import org.example.clases.Usuario;
import org.example.controladores.*;
import org.example.servicios.FakeServices;



public class Main {

    static FakeServices fakeServices = FakeServices.getInstancia();

    public static void main(String[] args) {

        Javalin app = Javalin.create(config -> {

            config.staticFiles.add(staticFileConfig -> {
                staticFileConfig.hostedPath = "/";
                staticFileConfig.directory = "/publico";
                staticFileConfig.location = Location.CLASSPATH;
                staticFileConfig.precompress = false;
                staticFileConfig.aliasCheck = null;
            });
        });
        app.start(getHerokuAssignedPort());


        app.get("/", ctx -> ctx.render("publico/login.html"));


        app.get("/inicio", ctx -> {



            Map<String, Object> modelo = new HashMap<>();

            modelo.put("usuarioLogueado", ctx.sessionAttribute("usuario"));



            ctx.render("/publico/inicio.html", modelo);


        });

        app.post("/autenticar", ctxContext -> {
            String nombreUsuario = ctxContext.formParam("usuario");
            String password = ctxContext.formParam("password");
            Usuario usuario = fakeServices.autenticarUsuario(nombreUsuario, password);
            if (usuario != null) {
                ctxContext.sessionAttribute("usuario", usuario);
                System.out.println("\n\n\n\n\n\nUsuario autenticado: " + usuario);
                ctxContext.redirect("/inicio"); // Redirigir al usuario a la página de inicio
            } else {
                ctxContext.redirect("/");
            }
        });

        app.get("/visualizar/{id}", ctx -> {

            // Articulo articulo = fakeServices.getArticuloPorId(ctx.pathParamAsClass("id", long.class).get());

            //List<Etiqueta> etiquetas = articulo.getListaEtiquetas();

            Usuario usuarioLogueado = ctx.sessionAttribute("usuario");
            if (usuarioLogueado != null) {

                System.out.println("\n\n\n\n\n\nUsuario autenticado: " + usuarioLogueado.getUsername());
            } else {
                System.out.println("\n\n\nNo hay usuario autenticado\n\n\n");
            }


            Map<String, Object> modelo = new HashMap<>();
            // modelo.put("titulo", "Articulo " + articulo.getId());
            // modelo.put("articulo", articulo);
            modelo.put("usuarioLogueado", usuarioLogueado);


            modelo.put("visualizar", true);


            //modelo.put("accion", "/visualizar/" + articulo.getId() + "/comentar");


            ctx.render("/publico/vistaArticulo.html", modelo);


        });

        app.post("/visualizar/{id}/comentar", ctx -> {

            //  Articulo articulo = fakeServices.getArticuloPorId(ctx.pathParamAsClass("id", long.class).get());

            ;

            long id = 0;
            id++;
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n"+id+"\n\n\n\n\n\n\n\n\n\n\n\n");


            Usuario usuario = ctx.sessionAttribute("usuario");

            String comentario = ctx.formParam("comentario");
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n"+comentario+"\n\n\n\n\n\n\n\n\n\n\n\n");
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n"+usuario.getUsername()+"\n\n\n\n\n\n\n\n\n\n\n\n");


            // Comentario nuevoComentario = new Comentario(id,comentario, usuario, articulo);


            // articulo.getListaComentarios().add(nuevoComentario);

            System.out.println("\n\n\n\n\n\nn\n\n\n\n\n\n");

            // ctx.redirect("/visualizar/" + articulo.getId());
        });

        app.get("/eliminar/{id}", ctx -> {
            //   fakeServices.eliminandoArticulo(ctx.pathParamAsClass("id", Integer.class).get());
            ctx.redirect("/inicio/");
        });





        new RecibirDatosControlador(app).aplicarRutas();

        new CookiesSesionesControlador(app).aplicarRutas();





        new ApiControlador(app).aplicarRutas();




        new CrudTradicionalControladorUsuario(app).aplicarRutas();
        new CrudTradicionalControladorRegistro(app).aplicarRutas();


    }


    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 7000;
    }






}
