package org.example.controladores;

import org.example.clases.Usuario;

import org.example.servicios.FakeServices;
import org.example.util.BaseControlador;
import org.example.util.NoExisteUsuarioException;
import io.javalin.Javalin;
import io.javalin.openapi.OpenApi;

import java.util.List;
import java.util.stream.Collectors;

import static io.javalin.apibuilder.ApiBuilder.*;
@OpenApi(path = "/api")
public class ApiControlador extends BaseControlador {

    private FakeServices fakeServices = FakeServices.getInstancia();
    //private NoExisteArticuloException noExisteArticuloException = NoExisteArticuloException.getInstancia();

    public ApiControlador(Javalin app) {
        super(app);
    }

    @Override
    public void aplicarRutas() {
        app.routes(() -> {
            path("/api", () -> {


                path("/usuario", () -> {
                    after(ctx -> {
                        ctx.header("Content-Type", "application/json");
                    });

                    get("/", ctx -> {
                        ctx.json(fakeServices.listarUsuarios());
                    });

                    get("/{username}", ctx -> {
                        ctx.json(fakeServices.getUsuarioPorUsername(ctx.pathParamAsClass("username", String.class).get()));
                    });

                    post("/", ctx -> {
                        //parseando la informacion del POJO debe venir en formato json.
                        Usuario tmp = ctx.bodyAsClass(Usuario.class);
                        //creando.
                        ctx.json(fakeServices.crearUsuario(tmp));
                    });

                    put("/", ctx -> {
                        //parseando la informacion del POJO.
                        Usuario tmp = ctx.bodyAsClass(Usuario.class);
                        //creando.
                        try {
                            ctx.json(fakeServices.actualizarUsuario(tmp));
                        } catch (NoExisteUsuarioException e) {
                            throw new RuntimeException(e);
                        }

                    });

                    delete("/{username}", ctx -> {
                        //creando.
                        ctx.json(fakeServices.eliminandoUsuario(ctx.pathParam("username")));
                    });
                });
            });
        });
        app.exception(NoExisteUsuarioException.class, (exception, ctx) -> {
            ctx.status(404);
            ctx.json(""+exception.getLocalizedMessage());
        });
        app.exception(NoExisteUsuarioException.class, (exception, ctx) -> {
            ctx.status(404);
            ctx.json(""+exception.getLocalizedMessage());
        });

    }
}
