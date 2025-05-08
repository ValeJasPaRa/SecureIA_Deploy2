package pe.edu.upc.secureia.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.secureia.dtos.CantidadEventosAlarmaAltaxTipoDispositivoDTO;
import pe.edu.upc.secureia.dtos.CantidadEventosPorIdInmuebleDTO;
import pe.edu.upc.secureia.dtos.Evento_DispositivoDTO;
import pe.edu.upc.secureia.entities.Evento_Dispositivo;
import pe.edu.upc.secureia.servicesinterfaces.IEvento_DispositivoService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/evento_dispositivo")
public class Evento_DispositivoController {

    @Autowired
    private IEvento_DispositivoService eventS;

    @GetMapping("/listar")
    @PreAuthorize("hasAnyAuthority('ROLE_INDEPENDIENTE','ROLE_ADMIN')")
    public List<Evento_DispositivoDTO> listar() {
        return eventS.list().stream().map(y->{
            ModelMapper m = new ModelMapper();
            return m.map(y, Evento_DispositivoDTO.class);
        }).collect(Collectors.toList());
    }

    @PostMapping("/insertar")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_PADRE_FAMILIA')")
    public void insertar(@RequestBody Evento_DispositivoDTO dto) {
        ModelMapper m = new ModelMapper();
        Evento_Dispositivo ev = m.map(dto, Evento_Dispositivo.class);
        eventS.insert(ev);
    }

    @GetMapping("/listarid/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_INDEPENDIENTE','ROLE_ADMIN','ROLE_PADRE_FAMILIA')")
    public Evento_DispositivoDTO buscarId(@PathVariable("id") int id) {
        ModelMapper m=new ModelMapper();
        Evento_DispositivoDTO dto=m.map(eventS.listId(id),Evento_DispositivoDTO.class);
        return dto;
    }

    @PutMapping("/modificar")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_PADRE_FAMILIA')")
    public void modificar(@RequestBody Evento_DispositivoDTO dto) {
        ModelMapper m=new ModelMapper();
        Evento_Dispositivo e=m.map(dto,Evento_Dispositivo.class);
        eventS.update(e);
    }

    @DeleteMapping("/eliminar/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_PADRE_FAMILIA')")
    public void eliminar(@PathVariable("id") int id) {eventS.delete(id);}

    //HU67: Alberto Rafael
    @GetMapping("/CantidadEventosAlertaAltaxTipoDispositivo")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_PADRE_FAMILIA')")
    public List<CantidadEventosAlarmaAltaxTipoDispositivoDTO> AlertaAltaxTipoDispositivo() {
        List<CantidadEventosAlarmaAltaxTipoDispositivoDTO> CantidadEventosAlarmaAltaxTipoDispositivoDTO=new ArrayList<>();
        List<String[]> fila=eventS.CantidadEventosAltaxTipoDispositivo();
        for(String[]columna:fila){
            CantidadEventosAlarmaAltaxTipoDispositivoDTO dto=new CantidadEventosAlarmaAltaxTipoDispositivoDTO();
            dto.setTipoDispositivo((columna[0]));
            dto.setCantidadEventosAlertaAlta(Double.parseDouble(columna[1]));
            CantidadEventosAlarmaAltaxTipoDispositivoDTO.add(dto);
        }
        return CantidadEventosAlarmaAltaxTipoDispositivoDTO;
    }

    //HU74: Alberto Rafael
    @GetMapping("/CantidadEventosPorIdInmueble")
    @PreAuthorize("hasAnyAuthority('ROLE_INDEPENDIENTE','ROLE_ADMIN')")
    public List<CantidadEventosPorIdInmuebleDTO> CantidadEventosPorIdInmuebleIngresado(@RequestParam int id ) {
        List<CantidadEventosPorIdInmuebleDTO> CantidadEventosPorIdInmuebleDTO=new ArrayList<>();
        List<String[]> fila=eventS.CantidadEventosPorIdInmuebleS(id);
        for(String[]columna:fila){
            CantidadEventosPorIdInmuebleDTO dto=new CantidadEventosPorIdInmuebleDTO();
            dto.setId_Inmueble(Integer.parseInt(columna[0]));
            dto.setNombre_Inmueble(columna[1]);
            dto.setDireccion_Inmueble(columna[2]);
            dto.setCantidad_Eventos(Integer.parseInt(columna[3]));
            CantidadEventosPorIdInmuebleDTO.add(dto);
        }
        return CantidadEventosPorIdInmuebleDTO;
    }



}
