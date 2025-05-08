package pe.edu.upc.secureia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.secureia.entities.Evento_Dispositivo;

import java.util.List;

@Repository
public interface IEvento_DispositivoRepository extends JpaRepository<Evento_Dispositivo, Integer> {

    @Query(value = "select tipo_dispositivo, count (*) as cantidad_eventos_alarma_alta\n" +
            " from evento_dispositivo ed\n" +
            " join dispositivo d on ed.id_dispositivo = d.id_dispositivo\n" +
            " where ed.nivel_alerta_evento = 'Alta'\n" +
            " group by d.tipo_dispositivo;\n",nativeQuery = true)
    List<String[]>CantidadEventosAltaxTDispo();

    //Alberto Rafael
    @Query(value = "select \n" +
            "  i.id_inmueble,\n" +
            "  i.nombre_inmueble,\n" +
            "  i.direccion_inmueble,\n" +
            "  count(ed.id_evento)as cantidad_eventos\n" +
            "from evento_dispositivo ed\n" +
            "join dispositivo d on ed.id_dispositivo = d.id_dispositivo\n" +
            "join inmueble i on d.id_inmueble = i.id_inmueble\n" +
            "where i.id_inmueble = :idInmueble " +
            "group by i.id_inmueble, i.nombre_inmueble, i.direccion_inmueble;",nativeQuery = true)
    List<String[]> CantidadEventosPorIdInmueble(@Param("idInmueble") int idInmueble);


}
