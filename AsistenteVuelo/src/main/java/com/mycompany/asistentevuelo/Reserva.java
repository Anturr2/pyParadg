package com.mycompany.asistentevuelo;

import java.time.LocalDateTime;

public class Reserva {
    private String codigo;
    private Usuario usuario;
    private Vuelo vuelo;
    private LocalDateTime fechaReserva;
    private String estado; // "CONFIRMADA", "PENDIENTE", "CANCELADA"
    private int numeroAsiento;
    
    // Constructor
    public Reserva(String codigo, Usuario usuario, Vuelo vuelo) {
        this.codigo = codigo;
        this.usuario = usuario;
        this.vuelo = vuelo;
        this.fechaReserva = LocalDateTime.now();
        this.estado = "PENDIENTE";
    }

    Reserva() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    // Getters y setters
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    
    public Vuelo getVuelo() { return vuelo; }
    public void setVuelo(Vuelo vuelo) { this.vuelo = vuelo; }
    
    public LocalDateTime getFechaReserva() { return fechaReserva; }
    public void setFechaReserva(LocalDateTime fechaReserva) { this.fechaReserva = fechaReserva; }
    
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    
    public int getNumeroAsiento() { return numeroAsiento; }
    public void setNumeroAsiento(int numeroAsiento) { this.numeroAsiento = numeroAsiento; }
    
    // Métodos para cambiar el estado de la reserva
    public void confirmar() {
        this.estado = "CONFIRMADA";
        try {
            vuelo.reservarAsiento();
        } catch (Exception e) {
            this.estado = "CANCELADA";
            throw new RuntimeException("No se pudo confirmar la reserva: " + e.getMessage());
        }
    }
    
    public void cancelar() {
        this.estado = "CANCELADA";
    }
    
    @Override
    public String toString() {
        return codigo + " - Vuelo: " + vuelo.getCodigo() + " - Estado: " + estado;
    }
}
