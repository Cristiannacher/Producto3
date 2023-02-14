package com.paracasa.spring.app.service.usuarioService;

import com.paracasa.spring.app.model.Usuario;
import com.paracasa.spring.app.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements IUsuarioService{

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Override
    public Usuario findByUsername(String username){
        return usuarioRepository.findByUsername(username);
    }

    @Override
    public Usuario registrar (Usuario u) {
        u.setPassword(passwordEncoder.encode(u.getPassword()));
        return usuarioRepository.save(u);
    }


}
