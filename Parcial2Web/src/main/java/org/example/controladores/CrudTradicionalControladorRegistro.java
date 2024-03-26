
package org.example.controladores;


import org.example.clases.Registro;
import org.example.servicios.FakeServices;
import org.example.util.BaseControlador;
import io.javalin.Javalin;
import java.util.*;

import static io.javalin.apibuilder.ApiBuilder.*;

public class CrudTradicionalControladorRegistro extends BaseControlador {

    FakeServices fakeServices = FakeServices.getInstancia();
    public CrudTradicionalControladorRegistro(Javalin app) {
        super(app);
    }


    @Override
    public void aplicarRutas() {
        app.routes(() -> {
            path("/crud-simple-registro/", () -> {

                get("/", ctx -> {
                    ctx.redirect("/crud-simple-registro/listar");
                });

                get("/listar", ctx -> {
                    List<Registro> lista = fakeServices.listarRegistros();
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
                    String id = (ctx.formParam("id"));
                    String nombre = ctx.formParam("nombre");
                    String sector = ctx.formParam("sector");
                    String nivelEscolar = ctx.formParam("nivelEscolar");
                    String usuario = ctx.formParam("usuario");
                    String latitud = ctx.formParam("latitud");
                    String longitud = ctx.formParam("longitud");
                    boolean estado = ctx.formParam("estado") != null;


                    Registro registros = new Registro( "1", "Cristian","La loteria", "Grado","19", false);
                    //Registro registros = new Registro( "1", "Cristian","La loteria", "Grado","admin","19", "18", false);
                   // Registro registros = new Registro( id,sector, nivelEscolar,latitud, longitud, estado);
                    //imprime el registro
                    System.out.println("\n\n\n\n\n\n\n\n\nRegistro: " + registros+"\n\n\n\n\n\n\n\n\n\n");
                    fakeServices.crearRegistro(registros);
                    ctx.redirect("/crud-simple-registro/");
                });




                get("/visualizar/{id}", ctx -> {
                    Registro registro = fakeServices.getRegistroPorId(((ctx.pathParam("id"))));
                    Map<String, Object> modelo = new HashMap<>();
                    modelo.put("titulo", "Formulario Visualizar Registro " + registro.getUsername());
                    modelo.put("registro", registro);
                    modelo.put("visualizar", true);
                    modelo.put("accion", "/crud-simple-registro/");
                    ctx.render("/templates/crud-tradicional/crearEditarVisualizarRegistro.html", modelo);
                });



                get("/editar/{id}", ctx -> {
                    Registro registro = fakeServices.getRegistroPorId(((ctx.pathParam("id"))));
                    //
                    Map<String, Object> modelo = new HashMap<>();
                    modelo.put("titulo", "Formulario Editar Registro " + registro.getUsername());
                    modelo.put("registro", registro);
                    modelo.put("accion", "/crud-simple-registro/editar");


                    ctx.render("/templates/crud-tradicional/crearEditarVisualizarRegistro.html", modelo);
                });

                post("/editar", ctx -> {

                    String username = ctx.formParam("username");
                    String nombre = ctx.formParam("nombre");
                    String password = ctx.formParam("password");

                    int id = (ctx.formParamAsClass("id", int.class).get());
                    String sector = ctx.formParam("sector");
                    String nivelEscolar = ctx.formParam("nivelEscolar");
                    String latitud = ctx.formParam("latitud");
                    String longitud = ctx.formParam("longitud");
                    boolean estado = ctx.formParam("estado") != null;

                    Registro registroExistente = fakeServices.getRegistroPorId(String.valueOf(id));

                    if (registroExistente != null) {
                        registroExistente.setSector(nombre);
                        registroExistente.setNivelEscolar(nivelEscolar);
                        registroExistente.setLatitud(latitud);
                        registroExistente.setLongitud(longitud);
                        registroExistente.setEstado(estado);
                        fakeServices.actualizarRegistro(registroExistente);
                    } else {
                        System.out.println("No se encontró el registro con el id: " + id);
                    }

                    ctx.redirect("/crud-simple-registro/");
                });





                get("/eliminar/{id}", ctx -> {
                    fakeServices.eliminandoRegistro((ctx.pathParam("id")));
                    ctx.redirect("/crud-simple-registro/");
                });

            });
        });
    }
}
