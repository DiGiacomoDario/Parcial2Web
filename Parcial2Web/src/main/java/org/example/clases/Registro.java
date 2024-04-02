package org.example.clases;


import jakarta.persistence.*;

@Entity
@Table(name = "registros")
public class Registro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "sector")
    private String sector;

    @Column(name = "nivel_escolar")
    private String nivelEscolar;

    @Column(name = "username")
    private String username;

    @Column(name = "latitud")
    private String latitud;

    @Column(name = "longitud")
    private String longitud;

    @Column(name = "estado")
    private boolean estado;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;



    //crea el constructor
    public Registro(String nombre,String sector, String nivelEscolar,String username ,String latitud, String longitud, boolean estado) {
        this.nombre = nombre;
        this.sector = sector;
        this.nivelEscolar = nivelEscolar;
        this.usuario = usuario;
        this.latitud = latitud;
        this.longitud = longitud;
        this.estado = estado;
    }

    public Registro() {

    }

    // Resto del código de la clase Registro



    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    //getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
                ", username='" + username + '\'' +
                ", latitud='" + latitud + '\'' +
                ", longitud='" + longitud + '\'' +
                ", estado=" + estado +
               '}';
}



}