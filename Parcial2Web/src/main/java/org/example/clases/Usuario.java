package org.example.clases;



public class Usuario {

    String username;
    String nombre;
    String password;

    boolean admintrator;

    boolean autor;




    public Usuario() {
    }

    public Usuario(String username, String nombre, String password, boolean admintrator, boolean autor) {
        this.username = username;
        this.nombre = nombre;
        this.password = password;
        this.admintrator = admintrator;
        this.autor = autor;
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

    public boolean isAutor() {
        return autor;
    }

    public void setAutor(boolean autor) {
        this.autor = autor;
    }


}


