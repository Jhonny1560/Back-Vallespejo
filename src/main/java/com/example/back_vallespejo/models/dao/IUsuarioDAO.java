package com.example.back_vallespejo.models.dao;

import com.example.back_vallespejo.models.entities.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface IUsuarioDAO extends CrudRepository<Usuario, Long> {
}
