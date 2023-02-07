package com.paracasa.spring.app.controller;

import javax.validation.Valid;

import com.paracasa.spring.app.model.Role;
import com.paracasa.spring.app.model.Usuario;
import com.paracasa.spring.app.repository.UsuarioRepository;
import com.paracasa.spring.app.service.roleService.IRoleService;
import com.paracasa.spring.app.service.roleService.RoleService;
import com.paracasa.spring.app.service.usuarioService.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
public class LoginController {
    @Autowired
    private IUsuarioService usuarioService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/auth/login")
    public String login (Model model) {
        model.addAttribute("usuario", new Usuario());
        return "login";
    }

    @PostMapping("/auth/login")
    public String loginPost () {
        return "home";
    }

    @GetMapping("/auth/registro")
    public String registroForm (Model model) {
        model.addAttribute("roles", roleService.findAll());
        model.addAttribute("usuario", new Usuario());
        return "register";
    }

    @PostMapping("/auth/registro")
    public String registro (@Valid @ModelAttribute Usuario usuario,
                            @RequestParam("role") String roleId,
                            BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "redirect:/auth/registro";
        } else {
            Optional<Role> selectedRole = roleService.findById(Long.parseLong(roleId));
            if(selectedRole.isPresent()){
                usuarioService.registrar(usuario);
                usuario.getRolesAssociated().addAll((List.of(selectedRole.get())));
                usuarioRepository.save(usuario);
            }
            System.out.println("selectedRole: " + selectedRole.get().getName());
            System.out.println("usuario: " + usuario.getNombre());
            model.addAttribute("usuario", usuario);

            if(Objects.equals(selectedRole.get().getName(), "admin")){
                return "redirect:/";
            }
        }
        return "redirect:/auth/login";
    }
}
