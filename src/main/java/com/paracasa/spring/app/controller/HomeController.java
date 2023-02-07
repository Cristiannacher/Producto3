package com.paracasa.spring.app.controller;

import javax.servlet.http.HttpSession;

import com.paracasa.spring.app.service.menuService.MenuService;
import com.paracasa.spring.app.service.roleService.RoleService;
import com.paracasa.spring.app.service.usuarioService.IUsuarioService;
import com.paracasa.spring.app.utils.CheckSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    private MenuService menuService;

    @Autowired
    private IUsuarioService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping("/")
    public String home(Model model, Authentication auth, HttpSession session){
        this.checkSession(auth, session,
                userService, roleService);
        model.addAttribute("menus", menuService.findAll());
        model.addAttribute("currentPage", "home");

        return "pedido";
    }

    private void checkSession(Authentication auth, HttpSession session,
                              IUsuarioService userService,
                              RoleService roleService){
        CheckSession currentSession = new CheckSession(auth, session,
                userService, roleService);
        currentSession.validate();
    }
}
