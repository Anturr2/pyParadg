package com.mycompany.asistentevuelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Itinerario {
    private String numeroVuelo;
    private String origen;
    private String destino;
    private LocalDateTime fechaHoraSalida;
    private LocalDateTime fechaHoraLlegada;
    private String puertaEmbarque;
    private String terminal;
    private String estado; // "Programado", "Demorado", "Cancelado", etc.
    
    public Itinerario() {
    }
    
    public Itinerario(String numeroVuelo, String origen, String destino, 
                     LocalDateTime fechaHoraSalida, LocalDateTime fechaHoraLlegada, 
                     String puertaEmbarque, String terminal) {
        this.numeroVuelo = numeroVuelo;
        this.origen = origen;
        this.destino = destino;
        this.fechaHoraSalida = fechaHoraSalida;
        this.fechaHoraLlegada = fechaHoraLlegada;
        this.puertaEmbarque = puertaEmbarque;
        this.terminal = terminal;
        this.estado = "Programado";
    }
    
    public String getHoraRecomendadaLlegada() {
        // Se recomienda llegar 2 horas antes para vuelos nacionales
        LocalDateTime horaRecomendada = fechaHoraSalida.minusHours(2);
        return horaRecomendada.format(DateTimeFormatter.ofPattern("HH:mm"));
    }
    
    public String getDuracionVuelo() {
        // Calcular la diferencia entre horario de salida y llegada
        long minutos = java.time.Duration.between(fechaHoraSalida, fechaHoraLlegada).toMinutes();
        long horas = minutos / 60;
        long minutosRestantes = minutos % 60;
        
        return String.format("%d h %d min", horas, minutosRestantes);
    }
    
    // Getters y Setters
    public String getNumeroVuelo() {
        return numeroVuelo;
    }
    
    public void setNumeroVuelo(String numeroVuelo) {
        this.numeroVuelo = numeroVuelo;
    }
    
    public String getOrigen() {
        return origen;
    }
    
    public void setOrigen(String origen) {
        this.origen = origen;
    }
    
    public String getDestino() {
        return destino;
    }
    
    public void setDestino(String destino) {
        this.destino = destino;
    }
    
    public LocalDateTime getFechaHoraSalida() {
        return fechaHoraSalida;
    }
    
    public void setFechaHoraSalida(LocalDateTime fechaHoraSalida) {
        this.fechaHoraSalida = fechaHoraSalida;
    }
    
    public LocalDateTime getFechaHoraLlegada() {
        return fechaHoraLlegada;
    }
    
    public void setFechaHoraLlegada(LocalDateTime fechaHoraLlegada) {
        this.fechaHoraLlegada = fechaHoraLlegada;
    }
    
    public String getPuertaEmbarque() {
        return puertaEmbarque;
    }
    
    public void setPuertaEmbarque(String puertaEmbarque) {
        this.puertaEmbarque = puertaEmbarque;
    }
    
    public String getTerminal() {
        return terminal;
    }
    
    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }
    
    public String getEstado() {
        return estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public String getFechaSalidaFormateada() {
        return fechaHoraSalida.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }
    
    public String getFechaLlegadaFormateada() {
        return fechaHoraLlegada.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }
}
