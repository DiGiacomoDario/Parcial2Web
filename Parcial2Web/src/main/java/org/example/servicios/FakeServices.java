package org.example.servicios;


import org.example.clases.Usuario;

import java.util.*;

public class FakeServices {
    private static FakeServices instancia;

    private List<Usuario> usuarios = new ArrayList<>();



    public static FakeServices getInstancia() {
        if (instancia == null) {
            instancia = new FakeServices();
        }
        return instancia;
    }


    public Usuario getUsuarioPorUsername(String username) {
        return usuarios.stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }

    public Object actualizarUsuario(Usuario usuario) {
        int index = -1;
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getUsername().equals(usuario.getUsername())) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            usuarios.set(index, usuario);
        }
        return null;
    }

    public Object eliminandoUsuario(String username) {
        usuarios.removeIf(u -> u.getUsername().equals(username));
        return null;
    }

    public Object crearUsuario(Usuario usuario) {
        usuarios.add(usuario);
        return null;
    }

    public List<Usuario> listarUsuarios() {
        return new ArrayList<>(usuarios);
    }

    public Usuario autenticarUsuario(String username, String password) {
        return usuarios.stream()
                .filter(u -> u.getUsername().equals(username) && u.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }





}