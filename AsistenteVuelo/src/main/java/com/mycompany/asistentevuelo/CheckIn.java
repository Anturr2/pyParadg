package com.mycompany.asistentevuelo;

import java.time.LocalDateTime;

public class CheckIn {
    private String codigoReserva;
    private LocalDateTime fechaHoraCheckIn;
    private boolean completado;
    private String asiento;
    private int numMaletas;
    private Reserva reserva;
    
    public CheckIn() {
        this.fechaHoraCheckIn = LocalDateTime.now();
        this.completado = false;
    }
    
    public CheckIn(String codigoReserva, String asiento, int numMaletas, Reserva reserva) {
        this.codigoReserva = codigoReserva;
        this.fechaHoraCheckIn = LocalDateTime.now();
        this.asiento = asiento;
        this.numMaletas = numMaletas;
        this.completado = false;
        this.reserva = reserva;
    }
    
    public boolean realizarCheckIn() {
        if (validarReserva()) {
            this.completado = true;
            return true;
        }
        return false;
    }
    
    private boolean validarReserva() {
        // Lógica para validar que la reserva exista y esté vigente
        return reserva != null && !codigoReserva.isEmpty();
    }
    
    // Getters y Setters
    public String getCodigoReserva() {
        return codigoReserva;
    }
    
    public void setCodigoReserva(String codigoReserva) {
        this.codigoReserva = codigoReserva;
    }
    
    public LocalDateTime getFechaHoraCheckIn() {
        return fechaHoraCheckIn;
    }
    
    public boolean isCompletado() {
        return completado;
    }
    
    public void setCompletado(boolean completado) {
        this.completado = completado;
    }
    
    public String getAsiento() {
        return asiento;
    }
    
    public void setAsiento(String asiento) {
        this.asiento = asiento;
    }
    
    public int getNumMaletas() {
        return numMaletas;
    }
    
    public void setNumMaletas(int numMaletas) {
        this.numMaletas = numMaletas;
    }
    
    public Reserva getReserva() {
        return reserva;
    }
    
    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }
}
