
package org.example.controladores;

import org.example.util.BaseControlador;
import io.javalin.Javalin;

import java.util.ArrayList;
import java.util.List;

public class RecibirDatosControlador extends BaseControlador {

    public RecibirDatosControlador(Javalin app) {
        super(app);
    }

    @Override
    public void aplicarRutas() {

        app.get("/parametros", ctx -> {
            List<String> salida = new ArrayList<>();
            salida.add("Mostrando todos los parametros enviados:");
            //listando la informacion.
            ctx.queryParamMap().forEach((key, lista) -> {
                salida.add(String.format("[%s] = [%s]", key, String.join(",", lista)));
            });
            //
            ctx.result(String.join("\n", salida));
        });


        app.get("/parametros/{id}/", ctx -> {
            //TODO: metodo para validar id..
            ctx.result("El articulo tiene el id: "+ctx.pathParam("id"));
        });


        app.get("/parametros/{id}/titulo/{titulo}", ctx -> {
            ctx.result("El articulo tiene el id: "+ctx.pathParam("id")+" - titulo: "+ctx.pathParam("titulo"));
        });

        app.get("/parametros/{para1}/{para2}/{para3}", ctx -> {
            ctx.result("hhhhh");
        });


        app.get("/parametros/{para4}/{para5}/{para6}", ctx -> {
            ctx.result("kkkkkk");
        });


        app.post("/parametros", ctx -> {
            System.out.println("El tipo de datos recibido: "+ctx.header("Content-Type")+ "Matricula:"+ctx.queryParam("matricula"));
            List<String> salida = new ArrayList<>();
            salida.add("Mostrando todos la informacion enviada en el cuerpo:");

            ctx.formParamMap().forEach((key, lista) -> {
                salida.add(String.format("[%s] = [%s]", key, String.join(",", lista)));
            });
            //
            ctx.result(String.join("\n", salida));
        });


        app.get("leerheaders", ctx -> {
            List<String> salida = new ArrayList<>();
            salida.add("Mostrando la informacion enviada en los headers:");

            ctx.headerMap().forEach((key, valor) -> {
                salida.add(String.format("[%s] = [%s]", key, String.join(",", valor)));
            });

            ctx.result(String.join("\n", salida));
        });
    }
}
