package pe.edu.upc.secureia.servicesinterfaces;

import pe.edu.upc.secureia.entities.Evento_Dispositivo;

import java.util.List;

public interface IEvento_DispositivoService {
    public List<Evento_Dispositivo> list();

    public void insert(Evento_Dispositivo event);

    public Evento_Dispositivo listId(int id);

    public void update(Evento_Dispositivo event);

    public void delete(int id);

    public List<String[]> CantidadEventosAltaxTipoDispositivo();

    public List<String[]> CantidadEventosPorIdInmuebleS(int idInmueble);
}
