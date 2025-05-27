package com.mycompany.asistentevuelo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;

public class MainApp extends Application {
    
    private static Stage primaryStage;
    
    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        cargarVistaInicial();
        configurarStagePrincipal();
        primaryStage.show();
    }
    
    private void cargarVistaInicial() {
        try {
            cambiarVista("/vistas/login.fxml");
        } catch (IOException e) {
            System.err.println("Error al cargar la vista inicial:");
            e.printStackTrace();
            System.exit(1);
        }
    }
    
    private void configurarStagePrincipal() {
        primaryStage.setTitle("Asistente de Vuelos - Aerolíneas PPA");
        primaryStage.setResizable(true);
        try {
            primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/imagenes/logo.png")));
        } catch (NullPointerException e) {
            System.err.println("Error: No se encontró el ícono de la aplicación");
        }
    }
    
    public static void cambiarVista(String fxmlPath) throws IOException {
        Parent root = FXMLLoader.load(MainApp.class.getResource(fxmlPath));
        Scene scene = new Scene(root);
        
        // Mantener estilos consistentes
        scene.getStylesheets().clear();
        scene.getStylesheets().add(MainApp.class.getResource("/estilos/estilo.css").toExternalForm());
        
        primaryStage.setScene(scene);
        ajustarTamañoVentana(fxmlPath);
    }
    
    private static void ajustarTamañoVentana(String fxmlPath) {
        switch (fxmlPath) {
            case "/vistas/login.fxml":
                primaryStage.setWidth(600);
                primaryStage.setHeight(400);
                break;
            case "/vistas/main.fxml":
                primaryStage.setWidth(800);
                primaryStage.setHeight(600);
                break;
            case "/vistas/menu.fxml":
                primaryStage.setWidth(1000);
                primaryStage.setHeight(700);
                break;
            default:
                primaryStage.setWidth(800);
                primaryStage.setHeight(600);
        }
    }
    
    public static Stage getPrimaryStage() {
        return primaryStage;
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
