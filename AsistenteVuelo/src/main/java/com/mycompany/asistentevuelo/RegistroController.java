package com.mycompany.asistentevuelo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegistroController {
    
    @FXML private TextField campoNombre;
    @FXML private TextField campoApellido;
    @FXML private TextField campoCorreo;
    @FXML private TextField campoTelefono;
    @FXML private PasswordField campoContrasena;
    @FXML private PasswordField campoConfirmarContrasena;
    
    // Referencia al gestor de usuarios
    private GestorUsuarios gestorUsuarios = GestorUsuarios.getInstance();
    
    @FXML
    private void registrar() {
        try {
            // Validar que todos los campos estén completos
            validarCamposCompletos();
            
            // Validar que las contraseñas coincidan
            validarContrasenas();
            
            // Registrar al usuario
            Usuario nuevoUsuario = gestorUsuarios.registrarUsuario(
                campoNombre.getText(),
                campoApellido.getText(),
                campoCorreo.getText(),
                campoTelefono.getText(),
                campoContrasena.getText()
            );
            
            // Mostrar mensaje de éxito
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Registro exitoso");
            alert.setHeaderText(null);
            alert.setContentText("¡Usuario registrado correctamente!");
            alert.showAndWait();
            
            // Volver a la pantalla de login
            volverALogin();
            
        } catch (Exception e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error de registro");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
    
    @FXML
    private void volverALogin() {
        try {
            // Cargar la vista de login
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/login.fxml"));
            Parent root = loader.load();
            
            // Mostrar la vista de login
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/estilos/estilo.css").toExternalForm());
            
            Stage stage = (Stage) campoNombre.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            mostrarError("Error al cargar la pantalla de login");
        }
    }
    
    // Validar que todos los campos estén completos
    private void validarCamposCompletos() throws Exception {
        if (campoNombre.getText().isEmpty() || 
            campoApellido.getText().isEmpty() || 
            campoCorreo.getText().isEmpty() || 
            campoTelefono.getText().isEmpty() || 
            campoContrasena.getText().isEmpty() || 
            campoConfirmarContrasena.getText().isEmpty()) {
            throw new Exception("Todos los campos son obligatorios");
        }
    }
    
    // Validar que las contraseñas coincidan
    private void validarContrasenas() throws Exception {
        if (!campoContrasena.getText().equals(campoConfirmarContrasena.getText())) {
            throw new Exception("Las contraseñas no coinciden");
        }
        
        if (campoContrasena.getText().length() < 6) {
            throw new Exception("La contraseña debe tener al menos 6 caracteres");
        }
    }
    
    private void mostrarError(String mensaje) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
