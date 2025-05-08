package pe.edu.upc.secureia.repositories;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.secureia.entities.Zona_Deteccion;

import java.util.List;

@Repository
public interface IZona_DeteccionRepository extends JpaRepository<Zona_Deteccion, Integer> {

    @Query(value = " Select i.id_inmueble, i.nombre_inmueble, COUNT(zd.id_zona) as cantidad_zonas_monitoriadas\n" +
            " from inmueble i\n" +
            " join zona_deteccion zd ON i.id_inmueble = zd.id_inmueble\n" +
            " where zd.monitoreo_activo_zona = true\n" +
            " group by  i.id_inmueble, i.nombre_inmueble;",nativeQuery = true)
    List<String[]> cantZonasMonitoriadas();

    @Query(value = "select zd.id_zona, zd.nombre_zona,\n" +
            "count(r.id_reconocimiento) as Cantidad_reconocimientos\n" +
            "from reconocimiento r\n" +
            "join evento_dispositivo ed on r.id_evento_dispositivo= ed.Id_evento\n" +
            "join dispositivo d on ed.id_dispositivo = d.id_dispositivo\n" +
            "join inmueble i on d.id_inmueble = i.id_inmueble\n" +
            "join zona_deteccion zd on zd.id_inmueble = i.id_inmueble\n" +
            "group by zd.id_zona, zd.nombre_zona\n" +
            "order by cantidad_reconocimientos desc;",nativeQuery = true)
    List<String[]> ZonasconMayorCantReconocimiento();

}
