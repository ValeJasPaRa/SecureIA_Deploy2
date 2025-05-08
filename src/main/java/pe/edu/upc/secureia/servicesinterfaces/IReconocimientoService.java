package pe.edu.upc.secureia.servicesinterfaces;

import org.springframework.data.repository.query.Param;
import pe.edu.upc.secureia.entities.Reconocimiento;

import java.time.LocalDate;
import java.util.List;

public interface IReconocimientoService {
    public List<Reconocimiento> list();

    public void insert(Reconocimiento reco);

    public Reconocimiento listId(int id);

    public void update(Reconocimiento reco);

    public void delete(int id);

    public List<Reconocimiento>ListarReconocimientosxTipoEvento(String tipo);

    public List<String[]> ReconocimientoxDia(LocalDate fecha);

    public List<String[]> CantidadReconocimientosxIdInmuebleS( int id);
}
