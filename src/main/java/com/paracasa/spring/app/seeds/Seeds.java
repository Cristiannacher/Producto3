package com.paracasa.spring.app.seeds;

import com.paracasa.spring.app.model.Menu;
import com.paracasa.spring.app.model.Product;
import com.paracasa.spring.app.model.Role;
import com.paracasa.spring.app.model.Usuario;
import com.paracasa.spring.app.repository.MenuRepository;
import com.paracasa.spring.app.repository.ProductRepository;
import com.paracasa.spring.app.repository.RoleRepository;
import com.paracasa.spring.app.repository.UsuarioRepository;
import com.paracasa.spring.app.service.roleService.RoleService;
import com.paracasa.spring.app.service.usuarioService.IUsuarioService;

import java.util.Arrays;
import java.util.List;


public class Seeds {
    private final MenuRepository menuRepository;
    private final ProductRepository productRepository;
    private final IUsuarioService usuarioService;
    private final RoleRepository roleRepository;
    private final UsuarioRepository usuarioRepository;

    public Seeds(MenuRepository menuRepository,
                 ProductRepository productRepository,
                 IUsuarioService usuarioService,
                 UsuarioRepository usuarioRepository,
                 RoleRepository roleRepository)  {
        this.menuRepository = menuRepository;
        this.productRepository = productRepository;
        this.usuarioService = usuarioService;
        this.roleRepository = roleRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public void generateSeeds(){
        Role userRole = new Role("user");
        Role adminRole = new Role("admin");
        roleRepository.save(userRole);
        roleRepository.save(adminRole);

        Usuario user = new Usuario("userTest", "test@test.com",
                "user","1234");
        usuarioService.registrar(user);
        user.getRolesAssociated().addAll((List.of(userRole)));
        usuarioRepository.save(user);

        Usuario admin = new Usuario("adminTest", "test@test.com",
                "admin","1234");
        usuarioService.registrar(admin);
        admin.getRolesAssociated().addAll((List.of(adminRole)));
        usuarioRepository.save(admin);

        Menu menuNike = new Menu("Nike", 30.5);
        Menu menuAdidas = new Menu("Adidas", 20);
        Menu menuJordan = new Menu("Jordan", 25);
        menuRepository.save(menuNike);
        menuRepository.save(menuAdidas);
        menuRepository.save(menuJordan);

        Product productAdidas1 = new Product("Adidas Superstars",123.00);
        Product productAdidas2 = new Product("Adidas Yeezy Boost 350 V2",170.00);
        Product productJordan1 = new Product("Air Jordan 1 Retro High",136.50);
        Product productJordan2 = new Product("Air Jordan 4 Retro Military",150.50);
        Product productNike1 = new Product("Nike Cortez Classic",200.00);
        Product productNike2 = new Product("Nike Hot Step Terra Nocta Black",225.00);
        productRepository.save(productAdidas1);
        productRepository.save(productAdidas2);
        productRepository.save(productJordan1);
        productRepository.save(productJordan2);
        productRepository.save(productNike1);
        productRepository.save(productNike2);

        productAdidas1.getMenusAssociated().add(menuAdidas);
        productRepository.save(productAdidas1);
        productAdidas1.getMenusAssociated().add(menuAdidas);
        productRepository.save(productAdidas2);

        productNike1.getMenusAssociated().add(menuNike);
        productRepository.save(productNike1);
        productNike1.getMenusAssociated().add(menuNike);
        productRepository.save(productNike2);

        productJordan2.getMenusAssociated().add(menuJordan);
        productRepository.save(productJordan1);
        productJordan2.getMenusAssociated().add(menuJordan);
        productRepository.save(productJordan2);
    }
}