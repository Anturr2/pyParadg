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

public class LoginController {
    
    @FXML private TextField campoCorreo;
    @FXML private PasswordField campoContrasena;
    
    // Referencia al gestor de usuarios
    private GestorUsuarios gestorUsuarios = GestorUsuarios.getInstance();
    
    @FXML
    private void iniciarSesion() {
        try {
            // Validar que todos los campos estén completos
            if (campoCorreo.getText().isEmpty() || campoContrasena.getText().isEmpty()) {
                throw new Exception("Por favor complete todos los campos");
            }
            
            // Autenticar al usuario
            Usuario usuario = gestorUsuarios.autenticar(
                campoCorreo.getText(),
                campoContrasena.getText()
            );
            
            // Cargar la vista del menú principal
            cargarMenuPrincipal(usuario);
            
        } catch (Exception e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error de inicio de sesión");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
    
    @FXML
    private void mostrarRegistro() {
        try {
            // Cargar la vista de registro
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/registro.fxml"));
            Parent root = loader.load();
            
            // Mostrar la vista de registro
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/estilos/estilo.css").toExternalForm());
            
            Stage stage = (Stage) campoCorreo.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            mostrarError("Error al cargar la pantalla de registro");
        }
    }
    
    private void cargarMenuPrincipal(Usuario usuario) {
        try {
            // Cargar la vista del menú principal
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/menu.fxml"));
            Parent root = loader.load();
            
            // Obtener el controlador del menú y establecer el usuario
            MenuController menuController = loader.getController();
            menuController.inicializarUsuario(usuario);
            
            // Mostrar la vista del menú principal
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/estilos/estilo.css").toExternalForm());
            
            Stage stage = (Stage) campoCorreo.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            mostrarError("Error al cargar el menú principal");
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
