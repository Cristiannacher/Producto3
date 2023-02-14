package com.paracasa.spring.app.service.usuarioService;

import com.paracasa.spring.app.model.Usuario;
import com.paracasa.spring.app.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<Usuario> findAll(){
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario findById(Long id){
        return usuarioRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Long id){
        usuarioRepository.deleteById(id);
    }

    @Override
    public void delete(Usuario usuario){
        usuarioRepository.delete(usuario);
    }
}
