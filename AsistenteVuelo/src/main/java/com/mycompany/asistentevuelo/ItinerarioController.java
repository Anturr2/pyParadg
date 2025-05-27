package com.mycompany.asistentevuelo;

import com.mycompany.asistentevuelo.util.UtilitarioAlertas;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ItinerarioController {
    
    @FXML private TextField txtNumeroVuelo;
    @FXML private Label lblNumeroVuelo;
    @FXML private Label lblOrigen;
    @FXML private Label lblDestino;
    @FXML private Label lblFechaSalida;
    @FXML private Label lblFechaLlegada;
    @FXML private Label lblPuerta;
    @FXML private Label lblTerminal;
    @FXML private Label lblEstado;
    @FXML private Label lblDuracion;
    @FXML private Label lblHoraRecomendada;
    @FXML private VBox panelDetalles;
    @FXML private GridPane gridDetalles;
    @FXML private ListView<String> listRecomendaciones;
    
    private List<Itinerario> itinerariosDisponibles;
    private Itinerario itinerarioActual;
    
    @FXML
    public void initialize() {
        // Inicializar componentes
        panelDetalles.setVisible(false);
        
        // Crear datos de ejemplo
        cargarItinerariosDeEjemplo();
    }
    
    private void cargarItinerariosDeEjemplo() {
        itinerariosDisponibles = new ArrayList<>();
        
        // Vuelo 1
        LocalDateTime salida1 = LocalDateTime.of(2025, 5, 25, 10, 30);
        LocalDateTime llegada1 = LocalDateTime.of(2025, 5, 25, 12, 45);
        Itinerario itinerario1 = new Itinerario("PA456", "Bogotá", "Madrid", 
                                               salida1, llegada1, "A15", "T1");
        
        // Vuelo 2
        LocalDateTime salida2 = LocalDateTime.of(2025, 5, 26, 8, 0);
        LocalDateTime llegada2 = LocalDateTime.of(2025, 5, 26, 11, 30);
        Itinerario itinerario2 = new Itinerario("PA123", "Madrid", "Nueva York", 
                                               salida2, llegada2, "B22", "T3");
        
        itinerariosDisponibles.add(itinerario1);
        itinerariosDisponibles.add(itinerario2);
    }
    
    @FXML
    private void buscarItinerario() {
        String numeroVuelo = txtNumeroVuelo.getText().trim();
        
        if (numeroVuelo.isEmpty()) {
            UtilitarioAlertas.mostrarAlerta("Error", 
                                         "Por favor ingrese un número de vuelo válido.");
            return;
        }
        
        // Buscar en los itinerarios disponibles
        itinerarioActual = null;
        for (Itinerario itinerario : itinerariosDisponibles) {
            if (itinerario.getNumeroVuelo().equalsIgnoreCase(numeroVuelo)) {
                itinerarioActual = itinerario;
                break;
            }
        }
        
        if (itinerarioActual != null) {
            mostrarDetallesItinerario();
        } else {
            UtilitarioAlertas.mostrarAlerta("No encontrado", 
                                         "No se encontró ningún vuelo con el número ingresado.");
            panelDetalles.setVisible(false);
        }
    }
    
    private void mostrarDetallesItinerario() {
        // Mostrar panel de detalles
        panelDetalles.setVisible(true);
        
        // Actualizar etiquetas con la información del itinerario
        lblNumeroVuelo.setText(itinerarioActual.getNumeroVuelo());
        lblOrigen.setText(itinerarioActual.getOrigen());
        lblDestino.setText(itinerarioActual.getDestino());
        lblFechaSalida.setText(itinerarioActual.getFechaSalidaFormateada());
        lblFechaLlegada.setText(itinerarioActual.getFechaLlegadaFormateada());
        lblPuerta.setText(itinerarioActual.getPuertaEmbarque());
        lblTerminal.setText(itinerarioActual.getTerminal());
        lblEstado.setText(itinerarioActual.getEstado());
        lblDuracion.setText(itinerarioActual.getDuracionVuelo());
        lblHoraRecomendada.setText(itinerarioActual.getHoraRecomendadaLlegada());
        
        // Agregar recomendaciones
        listRecomendaciones.getItems().clear();
        listRecomendaciones.getItems().addAll(
            "Llegue al aeropuerto al menos 2 horas antes del vuelo (" + 
             itinerarioActual.getHoraRecomendadaLlegada() + ")",
            "Tenga su documento de identidad y pasaporte listos",
            "Equipaje de mano permitido: 1 pieza de máximo 10kg",
            "Dirígase a la Terminal " + itinerarioActual.getTerminal() + 
            ", Puerta " + itinerarioActual.getPuertaEmbarque(),
            "Realice check-in online para ahorrar tiempo"
        );
    }
    
    @FXML
    private void limpiarBusqueda() {
        txtNumeroVuelo.clear();
        panelDetalles.setVisible(false);
    }
}
        