package pe.edu.upc.secureia.dtos;

import java.time.LocalDate;

public class CantidadReconocimientosPorDiaDTO {

    private LocalDate fecha;
    private int cantidad;

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}