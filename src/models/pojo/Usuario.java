package models.pojo;

// Plain Old Java Object (POJO) para Usuario
public class Usuario {
    private int id;
    private String nombre_usuario;
    private String contrasena;
    private String nombre;
    private String apellido;
    private int id_rol;

    public Usuario() {
    }

    public Usuario(int id, String nombre_usuario, String contrasena, String nombre, String apellido, int id_rol) {
        this.id = id;
        this.nombre_usuario = nombre_usuario;
        this.contrasena = contrasena;
        this.nombre = nombre;
        this.apellido = apellido;
        this.id_rol = id_rol;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getId_rol() {
        return id_rol;
    }

    public void setId_rol(int id_rol) {
        this.id_rol = id_rol;
    }
}
