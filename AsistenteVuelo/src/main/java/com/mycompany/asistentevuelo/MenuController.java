package com.mycompany.asistentevuelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class MenuController {
    
    @FXML private Label labelBienvenida;
    @FXML private TableView<Vuelo> tablaVuelos;
    @FXML private TableColumn<Vuelo, String> columnaCodigo;
    @FXML private TableColumn<Vuelo, String> columnaOrigen;
    @FXML private TableColumn<Vuelo, String> columnaDestino;
    @FXML private TableColumn<Vuelo, LocalDateTime> columnaFechaSalida;
    @FXML private TableColumn<Vuelo, Double> columnaPrecio;
    @FXML private ListView<Reserva> listaReservas;
    
    private Usuario usuarioActual;
    private List<Vuelo> vuelos;
    private List<Reserva> reservas;
    
    public void inicializarUsuario(Usuario usuario) {
        this.usuarioActual = usuario;
        labelBienvenida.setText("Bienvenido/a, " + usuario.getNombre() + " " + usuario.getApellido());
        
        // Inicializar datos de ejemplo
        inicializarDatos();
        
        // Configurar la tabla de vuelos
        configurarTablaVuelos();
        
        // Configurar la lista de reservas
        actualizarListaReservas();
    }
    
    private void inicializarDatos() {
        // Crear vuelos de ejemplo
        vuelos = new ArrayList<>();
        vuelos.add(new Vuelo("VL001", "Bogotá", "Medellín", 
                            LocalDateTime.now().plusDays(1), 
                            LocalDateTime.now().plusDays(1).plusHours(1), 
                            120000, 50));
        vuelos.add(new Vuelo("VL002", "Bogotá", "Cali", 
                            LocalDateTime.now().plusDays(2), 
                            LocalDateTime.now().plusDays(2).plusHours(1), 
                            150000, 30));
        vuelos.add(new Vuelo("VL003", "Medellín", "Cartagena", 
                            LocalDateTime.now().plusDays(3), 
                            LocalDateTime.now().plusDays(3).plusHours(2), 
                            180000, 40));
        
        // Crear reservas de ejemplo
        reservas = new ArrayList<>();
    }
    
    private void configurarTablaVuelos() {
        columnaCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        columnaOrigen.setCellValueFactory(new PropertyValueFactory<>("origen"));
        columnaDestino.setCellValueFactory(new PropertyValueFactory<>("destino"));
        columnaFechaSalida.setCellValueFactory(new PropertyValueFactory<>("fechaSalida"));
        columnaPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        
        tablaVuelos.setItems(FXCollections.observableArrayList(vuelos));
    }
    
    private void actualizarListaReservas() {
        listaReservas.setItems(FXCollections.observableArrayList(reservas));
    }
    
    @FXML
    private void reservarVuelo() {
        try {
            // Obtener el vuelo seleccionado
            Vuelo vueloSeleccionado = tablaVuelos.getSelectionModel().getSelectedItem();
            
            if (vueloSeleccionado == null) {
                throw new Exception("Por favor seleccione un vuelo para reservar");
            }
            
            // Crear una nueva reserva
            String codigoReserva = "RSV" + (reservas.size() + 1);
            Reserva nuevaReserva = new Reserva(codigoReserva, usuarioActual, vueloSeleccionado);
            
            // Confirmar la reserva
            nuevaReserva.confirmar();
            
            // Agregar la reserva a la lista
            reservas.add(nuevaReserva);
            
            // Actualizar la lista de reservas
            actualizarListaReservas();
            
            // Mostrar mensaje de éxito
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Reserva exitosa");
            alert.setHeaderText(null);
            alert.setContentText("¡Reserva creada correctamente!");
            alert.showAndWait();
            
        } catch (Exception e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error de reserva");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
    
    @FXML
    private void cancelarReserva() {
        try {
            // Obtener la reserva seleccionada
            Reserva reservaSeleccionada = listaReservas.getSelectionModel().getSelectedItem();
            
            if (reservaSeleccionada == null) {
                throw new Exception("Por favor seleccione una reserva para cancelar");
            }
            
            // Cancelar la reserva
            reservaSeleccionada.cancelar();
            
            // Actualizar la lista de reservas
            actualizarListaReservas();
            
            // Mostrar mensaje de éxito
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Cancelación exitosa");
            alert.setHeaderText(null);
            alert.setContentText("¡Reserva cancelada correctamente!");
            alert.showAndWait();
            
        } catch (Exception e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error de cancelación");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
    
    @FXML
    private void cerrarSesion() {
        try {
            // Cargar la vista de login
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/login.fxml"));
            Parent root = loader.load();
            
            // Mostrar la vista de login
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/estilos/estilo.css").toExternalForm());
            
            Stage stage = (Stage) labelBienvenida.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            mostrarError("Error al cerrar sesión");
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
