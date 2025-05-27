package com.mycompany.asistentevuelo;

import java.util.ArrayList;
import java.util.List;

// Patrón Singleton para gestionar los usuarios
public class GestorUsuarios {
    // Instancia única del gestor
    private static GestorUsuarios instancia;
    
    // Lista de usuarios registrados
    private List<Usuario> usuarios;
    private int contadorIds;
    
    // Constructor privado para el patrón Singleton
    private GestorUsuarios() {
        usuarios = new ArrayList<>();
        contadorIds = 1;
        
        // Agregar un usuario de prueba
        try {
            registrarUsuario("Admin", "Sistema", "admin@sistema.com", "1234567890", "admin123");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // Método para obtener la instancia única
    public static synchronized GestorUsuarios getInstance() {
        if (instancia == null) {
            instancia = new GestorUsuarios();
        }
        return instancia;
    }
    
    // Método para registrar un usuario
    public Usuario registrarUsuario(String nombre, String apellido, String correo, String telefono, String contrasena) throws Exception {
        // Validar que todos los campos no estén vacíos
        if (nombre == null || nombre.trim().isEmpty() ||
            apellido == null || apellido.trim().isEmpty() ||
            correo == null || correo.trim().isEmpty() ||
            telefono == null || telefono.trim().isEmpty() ||
            contrasena == null || contrasena.trim().isEmpty()) {
            throw new Exception("Todos los campos son obligatorios");
        }
        
        // Validar longitud de la contraseña
        if (contrasena.length() < 6) {
            throw new Exception("La contraseña debe tener al menos 6 caracteres");
        }
        
        // Validar que el correo no esté registrado
        if (existeUsuarioConCorreo(correo)) {
            throw new Exception("Ya existe un usuario con ese correo electrónico");
        }
        
        // Crear y agregar el usuario
        Usuario nuevoUsuario = new Usuario("USR" + contadorIds++, nombre, apellido, correo, telefono, contrasena);
        usuarios.add(nuevoUsuario);
        
        return nuevoUsuario;
    }
    
    // Método para autenticar un usuario
    public Usuario autenticar(String correo, String contrasena) throws Exception {
        // Validar que los campos no estén vacíos
        if (correo == null || correo.trim().isEmpty() ||
            contrasena == null || contrasena.trim().isEmpty()) {
            throw new Exception("Por favor ingrese correo y contraseña");
        }
        
        // Buscar el usuario por correo y contraseña
        for (Usuario usuario : usuarios) {
            if (usuario.getCorreo().equals(correo) && usuario.getContrasena().equals(contrasena)) {
                return usuario;
            }
        }
        
        // Si no se encuentra, lanzar excepción
        throw new Exception("Correo o contraseña incorrectos");
    }
    
    // Método para verificar si existe un usuario con un correo específico
    private boolean existeUsuarioConCorreo(String correo) {
        for (Usuario usuario : usuarios) {
            if (usuario.getCorreo().equals(correo)) {
                return true;
            }
        }
        return false;
    }
    
    // Método para obtener todos los usuarios (podría ser útil para administrador)
    public List<Usuario> obtenerTodosLosUsuarios() {
        return new ArrayList<>(usuarios); // Devolver una copia para proteger la lista original
    }
}
