package com.paracasa.spring.app.repository;

import com.paracasa.spring.app.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository <Usuario, Long>{
    public Usuario findByUsername(String username);
}
