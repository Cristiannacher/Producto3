package com.paracasa.spring.app.controller;

import com.paracasa.spring.app.model.Menu;
import com.paracasa.spring.app.service.menuService.MenuService;
import com.paracasa.spring.app.service.roleService.RoleService;
import com.paracasa.spring.app.service.usuarioService.IUsuarioService;
import com.paracasa.spring.app.utils.CheckSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class MenuController {
    @Autowired
    private MenuService menuService;
    @Autowired
    private IUsuarioService userService;
    @Autowired
    private RoleService roleService;

    @GetMapping("/menus")
    public String getMenus(Model model, Authentication auth, HttpSession session){
        this.checkSession(auth, session,
                userService, roleService);
        model.addAttribute("menus", menuService.findAll());
        model.addAttribute("currentPage", "menus");
        return "menus";
    }

    @GetMapping("/menu/{id}")
    public String getMenuDetail(Model model, @PathVariable Long id, Authentication auth, HttpSession session) throws Exception {
        this.checkSession(auth, session,
                userService, roleService);
        Optional<Menu> menu = Optional.ofNullable(menuService.findById(id).orElseThrow(() -> new Exception("Menu" + id + " not found")));
        if(menu.isPresent()) {
            model.addAttribute("menu", menu.get());
            model.addAttribute("currentPage", "menus");
        }
        return "edit_menu";
    }

    @PostMapping("/menu/update/{id}")
    public String updateMenu(@PathVariable("id") Long id,
                             @ModelAttribute("menu") Menu menuDetails) throws Exception {
        Optional<Menu> menu = Optional.ofNullable(menuService.findById(id).orElseThrow(() -> new Exception("Menu" + id + " not found")));
        if(menu.isPresent()){
            menu.get().setName(menuDetails.getName());
            menu.get().setPrice(menuDetails.getPrice());
            menuService.update(menu.get());
        }
        return "redirect:/menu/" + id;
    }

    @GetMapping("/menu/create")
    public String createMenu(Model model, Authentication auth, HttpSession session) {
        this.checkSession(auth, session,
                userService, roleService);
        model.addAttribute("menu", new Menu());
        model.addAttribute("currentPage", "menus");
        return "create_menu";
    }

    @PostMapping("/menu/save")
    public String newMenu(Model model, @ModelAttribute("menu") Menu menu) {
        menuService.create(menu);
        return "redirect:/";
    }

    private void checkSession(Authentication auth, HttpSession session,
                              IUsuarioService userService,
                              RoleService roleService){
        CheckSession currentSession = new CheckSession(auth, session,
                userService, roleService);
        currentSession.validate();
    }
}
