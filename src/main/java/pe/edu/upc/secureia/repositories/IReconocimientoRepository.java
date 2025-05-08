package pe.edu.upc.secureia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.secureia.entities.Reconocimiento;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IReconocimientoRepository extends JpaRepository <Reconocimiento, Integer>{

    @Query("SELECT r FROM Reconocimiento r WHERE r.id_evento_dispositivo.tipo_evento = :tipoEvento")
    List<Reconocimiento> findByTipoEvento(@Param("tipoEvento") String tipoEvento);//usando JPQL

    @Query(value = "SELECT DATE(r.fecha_hora_reconocimiento) AS fecha, COUNT(*) AS cantidad " +
            "FROM reconocimiento r " +
            "WHERE DATE(r.fecha_hora_reconocimiento) = :fecha " +
            "GROUP BY DATE(r.fecha_hora_reconocimiento)",
            nativeQuery = true)
    List<String[]> ReconocimientoxDiaEspecifico(@Param("fecha") LocalDate fecha);

    @Query(value = " select i.id_inmueble,i.nombre_inmueble,\n" +
            "  count(r.id_reconocimiento) as cantidad_reconocimientos\n" +
            " from reconocimiento r\n" +
            " join evento_dispositivo ed on r.id_evento_dispositivo = ed.Id_evento\n" +
            " join dispositivo d on ed.id_dispositivo = d.id_dispositivo\n" +
            " join inmueble i on d.id_inmueble = i.id_inmueble\n" +
            " where i.id_inmueble = :id\n" +
            " group by i.id_inmueble, i.nombre_inmueble;",nativeQuery = true)
    List<String[]> CantidadReconocimientosxIdInmueble(@Param("id") int id);
}
