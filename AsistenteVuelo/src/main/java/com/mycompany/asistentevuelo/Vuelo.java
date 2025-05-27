package com.mycompany.asistentevuelo;

import java.time.LocalDateTime;

public class Vuelo {
    private String codigo;
    private String origen;
    private String destino;
    private LocalDateTime fechaSalida;
    private LocalDateTime fechaLlegada;
    private double precio;
    private int asientosDisponibles;
    
    // Constructor
    public Vuelo(String codigo, String origen, String destino, 
                 LocalDateTime fechaSalida, LocalDateTime fechaLlegada, 
                 double precio, int asientosDisponibles) {
        this.codigo = codigo;
        this.origen = origen;
        this.destino = destino;
        this.fechaSalida = fechaSalida;
        this.fechaLlegada = fechaLlegada;
        this.precio = precio;
        this.asientosDisponibles = asientosDisponibles;
    }

    Vuelo() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    // Getters y setters
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    
    public String getOrigen() { return origen; }
    public void setOrigen(String origen) { this.origen = origen; }
    
    public String getDestino() { return destino; }
    public void setDestino(String destino) { this.destino = destino; }
    
    public LocalDateTime getFechaSalida() { return fechaSalida; }
    public void setFechaSalida(LocalDateTime fechaSalida) { this.fechaSalida = fechaSalida; }
    
    public LocalDateTime getFechaLlegada() { return fechaLlegada; }
    public void setFechaLlegada(LocalDateTime fechaLlegada) { this.fechaLlegada = fechaLlegada; }
    
    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }
    
    public int getAsientosDisponibles() { return asientosDisponibles; }
    public void setAsientosDisponibles(int asientosDisponibles) { this.asientosDisponibles = asientosDisponibles; }
    
    // Método para actualizar asientos disponibles tras una reserva
    public void reservarAsiento() {
        if (asientosDisponibles > 0) {
            asientosDisponibles--;
        } else {
            throw new RuntimeException("No hay asientos disponibles para este vuelo");
        }
    }
    
    @Override
    public String toString() {
        return codigo + " - " + origen + " a " + destino;
    }
}
