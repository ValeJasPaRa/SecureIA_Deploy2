package pe.edu.upc.secureia.dtos;

public class ZonasconMayorCantReconocimientoDTO {

    private int id_zona;
    private String nombre_Zona;
    private int cantidad_Reconocimientos;

    public int getId_zona() {
        return id_zona;
    }

    public void setId_zona(int id_zona) {
        this.id_zona = id_zona;
    }

    public String getNombre_Zona() {
        return nombre_Zona;
    }

    public void setNombre_Zona(String nombre_Zona) {
        this.nombre_Zona = nombre_Zona;
    }

    public int getCantidad_Reconocimientos() {
        return cantidad_Reconocimientos;
    }

    public void setCantidad_Reconocimientos(int cantidad_Reconocimientos) {
        this.cantidad_Reconocimientos = cantidad_Reconocimientos;
    }
}
