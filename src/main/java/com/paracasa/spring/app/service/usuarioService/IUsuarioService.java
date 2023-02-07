package com.paracasa.spring.app.service.usuarioService;

import com.paracasa.spring.app.model.Usuario;

public interface IUsuarioService {
    public Usuario findByUsername(String username);
    public Usuario registrar (Usuario u);


}
