package org.example.controladores;

import org.example.clases.Usuario;
import org.example.servicios.FakeServices;
import org.example.util.BaseControlador;
import io.javalin.Javalin;

import java.util.ArrayList;

import java.util.List;




public class CookiesSesionesControlador extends BaseControlador {
    FakeServices fakeServices = FakeServices.getInstancia();
    public static List<String> lista = new ArrayList<>();

    public CookiesSesionesControlador(Javalin app) {
        super(app);
        crearUsuarioAdminPorDefecto();


    }

    @Override
    public void aplicarRutas() {



        app.post("/login", ctx -> {
            String username = ctx.formParam("username");
            String password = ctx.formParam("password");
            Usuario usuario = fakeServices.autenticarUsuario(username, password);

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
        admin.setAutor(false);
        fakeServices.crearUsuario(admin);
    }





}
