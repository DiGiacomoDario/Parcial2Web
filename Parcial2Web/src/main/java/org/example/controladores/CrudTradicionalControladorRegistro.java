
package org.example.controladores;


import org.example.clases.Registro;
import org.example.clases.Usuario;
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
                    Usuario usuario = ctx.sessionAttribute("usuario");

                    // Obtén el usuario actualmente autenticado
                    fakeServices.getUsuarioPorUsername(ctx.sessionAttribute("username"));
                    //imprime el usuario actualmente autenticado
                    System.out.println("\n\n\n\n\n\nUsuario autenticadddo: " + usuario);
                    if (usuario == null) {

                        ctx.redirect("/");
                        return;
                    }
//                    String id = ctx.formParam("id");
                    //Crea una variable que cargue el id generado en la clase Registro con String.valueOf(++contador)
                    String id = String.valueOf(fakeServices.getContadorRegistros());
                    String sector = ctx.formParam("sector");
                    String nivelEscolar = ctx.formParam("nivelEscolar");
                    String latitud = ctx.formParam("latitud");
                    String longitud = ctx.formParam("longitud");
                    boolean estado = ctx.formParam("estado") != null;

                    Registro registro = new Registro(id, usuario.getNombre(), sector, nivelEscolar, usuario.getUsername(), "18", "19", estado);
                    registro.setUsuario(usuario);

                    System.out.println("\n\n\n\n\n\n\n\n\nRegistro: " + registro+"\n\n\n\n\n\n\n\n\n\n");
                    fakeServices.crearRegistro(registro);
                    ctx.redirect("/crud-simple-registro/");
                });



                get("/visualizar/{id}", ctx -> {
                    Registro registro = fakeServices.getRegistroPorId(((ctx.pathParam("id"))));
                    Map<String, Object> modelo = new HashMap<>();
                    modelo.put("titulo", "Formulario Visualizar Registro " + registro.getUsuario());
                    modelo.put("visualizar", true);
                    modelo.put("accion", "/crud-simple-registro/");
                    ctx.render("/templates/crud-tradicional/crearEditarVisualizarRegistro.html", modelo);
                });



                get("/editar/{id}", ctx -> {
                    Registro registro = fakeServices.getRegistroPorId(ctx.pathParamAsClass("id", String.class).get());
                    //
                    Map<String, Object> modelo = new HashMap<>();
                    modelo.put("titulo", "FORMULARIO EDITAR REGISTRO: " + registro.getUsuario().getNombre());
                    modelo.put("registro", registro);
                    modelo.put("accion", "/crud-simple-registro/editar");


                    ctx.render("/templates/crud-tradicional/crearEditarVisualizarRegistro.html", modelo);
                });

                post("/editar", ctx -> {

//                    String username = ctx.formParam("username");
//                    String nombre = ctx.formParam("nombre");
//                    String password = ctx.formParam("password");

                    //String id = String.valueOf(fakeServices.getContadorRegistros());
               /*     if (id == null) {
                        id = "1";
                    }*/

                    //obten el id del registro a editar
                    String id = ctx.formParam("id");
                    String sector = ctx.formParam("sector");
                    String nivelEscolar = ctx.formParam("nivelEscolar");
//                    String latitud = ctx.formParam("latitud");
//                    String longitud = ctx.formParam("longitud");
//                    boolean estado = ctx.formParam("estado") != null;
                    System.out.println("\n\n\n\n\n\n\n\n\nRegistro existente: " + id+"\n\n\n\n\n\n\n\n\n\n");
                    Registro registroExistente = fakeServices.getRegistroPorId(id);

                    //imprime el registro existente
                    System.out.println("\n\n\n\n\n\n\n\n\nRegistro existente: " + registroExistente+"\n\n\n\n\n\n\n\n\n\n");
                    if (registroExistente != null) {
//                        registroExistente.
                        registroExistente.setSector(sector);
                        registroExistente.setNivelEscolar(nivelEscolar);
//                        registroExistente.setLatitud(latitud);
//                        registroExistente.setLongitud(longitud);
//                        registroExistente.setEstado(estado);
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
