package com.mycompany.asistentevuelo;

import com.mycompany.asistentevuelo.util.UtilitarioAlertas;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class CheckInController {
    
    @FXML private TextField txtCodigoReserva;
    @FXML private ComboBox<String> comboAsientos;
    @FXML private Spinner<Integer> spinnerMaletas;
    @FXML private CheckBox checkDocumentosListos;
    @FXML private Button btnConfirmarCheckIn;
    @FXML private Label lblEstadoCheckIn;
    @FXML private VBox panelDetallesVuelo;
    @FXML private Label lblNumeroVuelo;
    @FXML private Label lblDestino;
    @FXML private Label lblFechaHora;
    
    private CheckIn checkIn;
    private Reserva reservaActual;
    
    @FXML
    public void initialize() {
        // Inicializar componentes
        spinnerMaletas.setValueFactory(
            new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 3, 0)
        );
        
        // Cargar opciones de asientos (ejemplo)
        comboAsientos.getItems().addAll(
            "A1", "A2", "A3", "B1", "B2", "B3", "C1", "C2", "C3"
        );
        
        // Estado inicial
        panelDetallesVuelo.setVisible(false);
        btnConfirmarCheckIn.setDisable(true);
        
        // Validar cuando se active el checkbox
        checkDocumentosListos.selectedProperty().addListener((obs, oldVal, newVal) -> {
            btnConfirmarCheckIn.setDisable(!newVal);
        });
    }
    
    @FXML
    private void buscarReserva() {
        String codigoReserva = txtCodigoReserva.getText().trim();
        
        if (codigoReserva.isEmpty()) {
            UtilitarioAlertas.mostrarAlerta("Error", 
                                          "Por favor ingrese un código de reserva válido.");
            return;
        }
        
        // Simulación de búsqueda de reserva
        // En un caso real, buscaría en una base de datos
        if (codigoReserva.equals("ABC123")) {
            // Datos de prueba
            reservaActual = new Reserva();
            Vuelo vuelo = new Vuelo();
            vuelo.setCodigo("PA456");
            vuelo.setDestino("Madrid");
            reservaActual.setVuelo(vuelo);
            
            // Mostrar información del vuelo
            lblNumeroVuelo.setText("Vuelo: " + vuelo.getCodigo());
            lblDestino.setText("Destino: " + vuelo.getDestino());
            lblFechaHora.setText("Fecha: 25/05/2025 10:30");
            
            panelDetallesVuelo.setVisible(true);
            
            // Crear objeto CheckIn
            checkIn = new CheckIn();
            checkIn.setCodigoReserva(codigoReserva);
            checkIn.setReserva(reservaActual);
        } else {
            UtilitarioAlertas.mostrarAlerta("No encontrado", 
                                         "No se encontró ninguna reserva con el código ingresado.");
            panelDetallesVuelo.setVisible(false);
        }
    }
    
    @FXML
    private void confirmarCheckIn() {
        if (comboAsientos.getValue() == null) {
            UtilitarioAlertas.mostrarAlerta("Error", 
                                         "Por favor seleccione un asiento.");
            return;
        }
        
        checkIn.setAsiento(comboAsientos.getValue());
        checkIn.setNumMaletas(spinnerMaletas.getValue());
        
        if (checkIn.realizarCheckIn()) {
            lblEstadoCheckIn.setText("Check-In completado exitosamente.");
            UtilitarioAlertas.mostrarAlerta("Éxito", 
                                         "Check-In realizado con éxito para el vuelo " + 
                                         reservaActual.getVuelo().getCodigo() + 
                                         ".\nAsiento asignado: " + checkIn.getAsiento() +
                                         "\nMaletas registradas: " + checkIn.getNumMaletas());
            
            // Aquí podría generar/mostrar tarjeta de embarque
        } else {
            lblEstadoCheckIn.setText("Error al realizar Check-In.");
            UtilitarioAlertas.mostrarAlerta("Error", 
                                         "No se pudo completar el Check-In. Verifique su reserva.");
        }
    }
    
    @FXML
    private void limpiarFormulario() {
        txtCodigoReserva.clear();
        comboAsientos.setValue(null);
        spinnerMaletas.getValueFactory().setValue(0);
        checkDocumentosListos.setSelected(false);
        panelDetallesVuelo.setVisible(false);
        lblEstadoCheckIn.setText("");
    }
}
