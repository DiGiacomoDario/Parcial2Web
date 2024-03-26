package org.example.clases;

public class Registro extends Usuario{
    private static long contador = 0;
    String id;
    String sector;
    String nivelEscolar;
    String latitud;
    String longitud;
    boolean estado;



    //crea el constructor


    public Registro(String id, String sector, String nivelEscolar, String latitud, String longitud, boolean estado) {

        this.id = String.valueOf(++contador);
        this.sector = sector;
        this.nivelEscolar = nivelEscolar;
        this.latitud = latitud;
        this.longitud = longitud;
        this.estado = estado;
    }

    //getters y setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
                ", sector='" + sector + '\'' +
                ", nivelEscolar='" + nivelEscolar + '\'' +
                ", latitud='" + latitud + '\'' +
                ", longitud='" + longitud + '\'' +
                ", estado=" + estado +
                '}';
    }



}
