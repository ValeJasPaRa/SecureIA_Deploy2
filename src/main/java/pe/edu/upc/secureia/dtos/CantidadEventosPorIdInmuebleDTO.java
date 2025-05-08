package pe.edu.upc.secureia.dtos;

public class CantidadEventosPorIdInmuebleDTO {
    private int id_Inmueble;
    private String nombre_Inmueble;
    private String direccion_Inmueble;
    private int Cantidad_Eventos;

    public int getId_Inmueble() {
        return id_Inmueble;
    }

    public void setId_Inmueble(int id_Inmueble) {
        this.id_Inmueble = id_Inmueble;
    }

    public String getNombre_Inmueble() {
        return nombre_Inmueble;
    }

    public void setNombre_Inmueble(String nombre_Inmueble) {
        this.nombre_Inmueble = nombre_Inmueble;
    }

    public String getDireccion_Inmueble() {
        return direccion_Inmueble;
    }

    public void setDireccion_Inmueble(String direccion_Inmueble) {
        this.direccion_Inmueble = direccion_Inmueble;
    }

    public int getCantidad_Eventos() {
        return Cantidad_Eventos;
    }

    public void setCantidad_Eventos(int cantidad_Eventos) {
        Cantidad_Eventos = cantidad_Eventos;
    }
}
