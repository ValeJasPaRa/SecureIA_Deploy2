package pe.edu.upc.secureia.dtos;

public class CantidadReconocimientosxIdInmuebleDTO {
   private int id_inmueble;
   private String Nombre_inmueble;
   private int cantidad_reconocimientos;

    public int getId_inmueble() {
        return id_inmueble;
    }

    public void setId_inmueble(int id_inmueble) {
        this.id_inmueble = id_inmueble;
    }

    public String getNombre_inmueble() {
        return Nombre_inmueble;
    }

    public void setNombre_inmueble(String nombre_inmueble) {
        Nombre_inmueble = nombre_inmueble;
    }

    public int getCantidad_reconocimientos() {
        return cantidad_reconocimientos;
    }

    public void setCantidad_reconocimientos(int cantidad_reconocimientos) {
        this.cantidad_reconocimientos = cantidad_reconocimientos;
    }
}
