package pe.edu.upc.secureia.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.secureia.dtos.*;
import pe.edu.upc.secureia.entities.Usuario;
import pe.edu.upc.secureia.servicesinterfaces.IUsuarioService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioS;

    @GetMapping("/listar")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<UserMostrarDTO> listar() {
        return usuarioS.list().stream().map(y->{
            ModelMapper m = new ModelMapper();
            return m.map(y, UserMostrarDTO.class);
        }).collect(Collectors.toList());
    }


    /*@GetMapping("/listarCompleto")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<UsuarioDTO> listarCompleto() {
        return usuarioS.list().stream().map(y->{
            ModelMapper m = new ModelMapper();
            return m.map(y, UsuarioDTO.class);
        }).collect(Collectors.toList());
    } //solo para obtener el body de modificar */

    @PostMapping("/insertar")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void insertar(@RequestBody UsuarioDTO u_dto) {
        ModelMapper m = new ModelMapper();
        Usuario usuario = m.map(u_dto, Usuario.class);
        usuarioS.insert(usuario);
    }


    @GetMapping("/listarid/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public UserMostrarDTO buscarId(@PathVariable("id") int id) {
        ModelMapper m=new ModelMapper();
        UserMostrarDTO dto=m.map(usuarioS.listId(id),UserMostrarDTO.class);
        return dto;
    }

    @PutMapping("/modificar")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void modificar(@RequestBody UsuarioDTO dto) {
        ModelMapper m=new ModelMapper();
        Usuario u=m.map(dto,Usuario.class);
        usuarioS.update(u);
    }

    @DeleteMapping("/eliminar/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void eliminar(@PathVariable("id") int id) {usuarioS.delete(id);}

    //HU61: Valery Pari
    @GetMapping("/buscaruserxname")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<UserxNombreDTO> BuscarUsuarioporNombre(@RequestParam String nombre) {
        return usuarioS.BuscarUsuarioxNombre(nombre).stream().map(y->{
            ModelMapper m = new ModelMapper();
            return m.map(y, UserxNombreDTO.class);
        }).collect(Collectors.toList());
    }

    //HU62: Valery Pari
    @GetMapping("/cantidadUsersxMonthxYear")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<CantidadUsuariosxMesyAnioDTO> CantUsuariosxMESyANIO(@RequestParam int year,int month) {
        List<CantidadUsuariosxMesyAnioDTO> CantidadUsuariosxMesyAnioDTO=new ArrayList<>();
        List<String[]> fila=usuarioS.ObtenerCantidadUsuariosxmesyanio(year,month);
        for(String[]columna:fila){
            CantidadUsuariosxMesyAnioDTO dto=new CantidadUsuariosxMesyAnioDTO();
            dto.setAño(Integer.parseInt(columna[0]));
            dto.setMes(Integer.parseInt(columna[1]));
            dto.setCantidad_de_usuarios(Double.parseDouble(columna[2]));
            CantidadUsuariosxMesyAnioDTO.add(dto);
        }
        return CantidadUsuariosxMesyAnioDTO;
    }


}
