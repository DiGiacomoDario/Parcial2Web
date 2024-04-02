package org.example.clases;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;

@Entity
public class Usuario {
    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "password")
    private String password;

    @Column(name = "admintrator")
    protected boolean admintrator;

    @Column(name = "usuario")
    protected boolean usuario;



    public Usuario() {
    }

    public Usuario(String username, String nombre, String password, boolean admintrator, boolean usuario) {
        this.username = username;
        this.nombre = nombre;
        this.password = password;
        this.admintrator = admintrator;
        this.usuario = usuario;
    }

    public Usuario(String id, String nombre, String usuario) {
        this.username = id;
        this.nombre = nombre;
        this.password = usuario;

    }

    public Usuario(String s) {
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

    public boolean isUsuario() {
        return usuario;
    }

    public void setUsuario(boolean usuario) {
        this.usuario = usuario;
    }


}


