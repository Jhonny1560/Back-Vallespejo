package com.example.back_vallespejo.controladores;

import com.example.back_vallespejo.models.entities.Usuario;
import com.example.back_vallespejo.models.dto.UsuarioDTO;
import com.example.back_vallespejo.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;

    /*Get para obtener todos los usuarios*/
    @GetMapping("/usuario")
    public List<Usuario> getAll(){

        return usuarioService.getAll();
    }

    @PostMapping("/usuario")
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario registrarUsuarioSimple(@RequestBody UsuarioDTO usuarioDTO){

        return usuarioService.registrarUsuarioDTO(usuarioDTO);
    }

    @GetMapping("/usuario/{id}")
    public Usuario getUsuarioById(@PathVariable Long id){

        return usuarioService.findById(id);
    }

    @DeleteMapping("/usuario/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarUsuario(@PathVariable Long id){
        Usuario usuario = usuarioService.findById(id);
        if(usuario != null){
            usuarioService.delete(usuario);
        }
    }
}
