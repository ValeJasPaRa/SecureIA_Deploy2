package pe.edu.upc.secureia.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.secureia.dtos.CantidadReconocimientosPorDiaDTO;
import pe.edu.upc.secureia.dtos.CantidadReconocimientosxIdInmuebleDTO;
import pe.edu.upc.secureia.dtos.ReconocimientoDTO;
import pe.edu.upc.secureia.entities.Reconocimiento;
import pe.edu.upc.secureia.servicesinterfaces.IReconocimientoService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/reconocimiento")
public class ReconocimientoController {
    @Autowired
    private IReconocimientoService recoS;

    @GetMapping("/listar")
    @PreAuthorize("hasAnyAuthority('ROLE_INDEPENDIENTE','ROLE_ADMIN')")
    public List<ReconocimientoDTO> listar() {
        return recoS.list().stream().map(y->{
            ModelMapper m = new ModelMapper();
            return m.map(y, ReconocimientoDTO.class);
        }).collect(Collectors.toList());
    }

    @PostMapping("/insertar")
    @PreAuthorize("hasAnyAuthority('ROLE_PADRE_FAMILIA','ROLE_ADMIN')")
    public void insertar(@RequestBody ReconocimientoDTO dto) {
        ModelMapper m = new ModelMapper();
        Reconocimiento re = m.map(dto, Reconocimiento.class);
        recoS.insert(re);
    }

    @GetMapping("/listarid/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_PADRE_FAMILIA', 'ROLE_ADMIN')")
    public ReconocimientoDTO buscarId(@PathVariable("id") int id) {
        ModelMapper m=new ModelMapper();
        ReconocimientoDTO dto=m.map(recoS.listId(id),ReconocimientoDTO.class);
        return dto;
    }

    @PutMapping("/modificar")
    @PreAuthorize("hasAnyAuthority('ROLE_PADRE_FAMILIA', 'ROLE_ADMIN')")
    public void modificar(@RequestBody ReconocimientoDTO dto) {
        ModelMapper m=new ModelMapper();
        Reconocimiento u=m.map(dto,Reconocimiento.class);
        recoS.update(u);
    }

    @DeleteMapping("/eliminar/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void eliminar(@PathVariable("id") int id) {recoS.delete(id);}


    //HU65: Dante Carhuaz
    @GetMapping("/ListarReconocimientoxTipoEvento")
    @PreAuthorize("hasAnyAuthority('ROLE_INDEPENDIENTE','ROLE_ADMIN')")
    public List<ReconocimientoDTO> ListarReconocimientoporTipoEvento(@RequestParam String tipo) {
        return recoS.ListarReconocimientosxTipoEvento(tipo).stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, ReconocimientoDTO.class);
        }).collect(Collectors.toList());
    }

    //HU73: Dante Carhuaz
    @GetMapping("/CantidadReconocimientosxDiaEspecifico")
    @PreAuthorize("hasAnyAuthority('ROLE_INDEPENDIENTE','ROLE_ADMIN')")
    public List<CantidadReconocimientosPorDiaDTO> CantidadReconocimientoxDia(@RequestParam LocalDate Ingresar_Fecha) {
        List<CantidadReconocimientosPorDiaDTO> dtoLista=new ArrayList<>();
        List<String[]> fila=recoS.ReconocimientoxDia(Ingresar_Fecha);
        for(String[]columna:fila){
            CantidadReconocimientosPorDiaDTO dto=new CantidadReconocimientosPorDiaDTO();
            dto.setFecha(LocalDate.parse(columna[0]));
            dto.setCantidad(Integer.parseInt(columna[1]));
            dtoLista.add(dto);
        }
        return dtoLista;
    }

    //HU75: Alberto Avila
    @GetMapping("/CantidadReconocimientosxIdInmueble")
    @PreAuthorize("hasAnyAuthority('ROLE_INDEPENDIENTE','ROLE_ADMIN')")
    public List<CantidadReconocimientosxIdInmuebleDTO> CantidadReconocimientosxIdInmueble(@RequestParam int id) {
        List<CantidadReconocimientosxIdInmuebleDTO> dtoLista=new ArrayList<>();
        List<String[]> fila=recoS.CantidadReconocimientosxIdInmuebleS(id);
        for(String[]columna:fila){
            CantidadReconocimientosxIdInmuebleDTO dto=new CantidadReconocimientosxIdInmuebleDTO();
            dto.setId_inmueble(Integer.parseInt(columna[0]));
            dto.setNombre_inmueble(columna[1]);
            dto.setCantidad_reconocimientos(Integer.parseInt(columna[2]));
            dtoLista.add(dto);
        }
        return dtoLista;
    }

}
