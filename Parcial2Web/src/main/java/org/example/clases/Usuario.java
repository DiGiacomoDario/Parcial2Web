package org.example.clases;



public class Usuario {

    String username;
    String nombre;
    String password;

    boolean admintrator;

    boolean usuario;




    public Usuario() {
    }

    public Usuario(String username, String nombre, String password, boolean admintrator, boolean usuario) {
        this.username = username;
        this.nombre = nombre;
        this.password = password;
        this.admintrator = admintrator;
        this.usuario = usuario;
    }



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmintrator() {
        return admintrator;
    }

    public void setAdmintrator(boolean admintrator) {
        this.admintrator = admintrator;
    }

    public boolean isusuario() {
        return usuario;
    }

    public void setusuario(boolean usuario) {
        this.usuario = usuario;
    }


}


