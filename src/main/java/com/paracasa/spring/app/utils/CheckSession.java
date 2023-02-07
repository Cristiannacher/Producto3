package com.paracasa.spring.app.utils;

import com.paracasa.spring.app.model.Role;
import com.paracasa.spring.app.model.Usuario;
import com.paracasa.spring.app.service.roleService.RoleService;
import com.paracasa.spring.app.service.usuarioService.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class CheckSession {
    private final Authentication auth;
    private final HttpSession session;
    private final IUsuarioService userService;
    private final RoleService roleService;

    public CheckSession(Authentication auth, HttpSession session,
                        IUsuarioService userService, RoleService roleService) {
        this.auth = auth;
        this.session = session;
        this.userService = userService;
        this.roleService = roleService;
    }

    public void validate(){
        String userName = null;
        if(auth != null) {
            userName = auth.getName();
        }
        if(session.getAttribute("usuario") == null) {
            Usuario usuario = this.userService.findByUsername(userName);

            if (usuario != null) {
                Set<Role> userRoles = usuario.getRolesAssociated();
                List<String> roles = new ArrayList<>();
                for (Role userRole : userRoles) {
                    assert false;
                    roles.add(userRole.getName());
                }
                assert false;
                session.setAttribute("role", roles.get(0));
                usuario.setPassword(null);
                session.setAttribute("usuario", usuario);
            }
        }
    }
}
