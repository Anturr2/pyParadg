package com.mycompany.asistentevuelo;

public class Usuario {
    private String id;
    private String nombre;
    private String apellido;
    private String correo;
    private String telefono;
    private String contrasena;
    
    // Constructor
    public Usuario(String id, String nombre, String apellido, String correo, String telefono, String contrasena) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.telefono = telefono;
        this.contrasena = contrasena;
    }
    
    // Getters y setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    
    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
    
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    
    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { 
        if (contrasena.length() >= 6) {
            this.contrasena = contrasena;
        } else {
            throw new IllegalArgumentException("La contraseña debe tener al menos 6 caracteres");
        }
    }
    
    @Override
    public String toString() {
        return nombre + " " + apellido;
    }
}
