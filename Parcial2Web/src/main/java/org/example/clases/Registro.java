package org.example.clases;

public class Registro{
    private static long contador = 0;
    String id;
    String nombre;
    String sector;
    String nivelEscolar;

    String user;
    String latitud;
    String longitud;
    boolean estado;
    Usuario usuario = new Usuario();


    //crea el constructor


    public Registro(String id,String nombre,String sector, String nivelEscolar,String user ,String latitud, String longitud, boolean estado) {

        this.id = String.valueOf(++contador);
        this.nombre = nombre;
        this.sector = sector;
        this.nivelEscolar = nivelEscolar;
        this.usuario = usuario;
        this.latitud = latitud;
        this.longitud = longitud;
        this.estado = estado;
    }

    // Resto del código de la clase Registro



    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    //getters y setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getNivelEscolar() {
        return nivelEscolar;
    }

    public void setNivelEscolar(String nivelEscolar) {
        this.nivelEscolar = nivelEscolar;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Registro{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", sector='" + sector + '\'' +
                ", nivelEscolar='" + nivelEscolar + '\'' +
                ", user='" + user + '\'' +
                ", latitud='" + latitud + '\'' +
                ", longitud='" + longitud + '\'' +
                ", estado=" + estado +
                '}';
    }



}
