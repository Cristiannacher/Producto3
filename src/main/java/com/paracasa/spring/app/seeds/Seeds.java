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

        Menu menuLibanes = new Menu("Libanes", 30.5);
        Menu menuVeggie = new Menu("Veggie", 20);
        Menu menuBurguer = new Menu("Libanes", 25);
        menuRepository.save(menuLibanes);
        menuRepository.save(menuVeggie);
        menuRepository.save(menuBurguer);

        Product productPollo = new Product("Pollo",5.00);
        Product productSeitan = new Product("Seitan",7.00);
        Product productTofu = new Product("Tofu",6.50);
        Product productTernera = new Product("Ternera",5.50);
        Product productSetas = new Product("Setas",2.00);
        productRepository.save(productPollo);
        productRepository.save(productSeitan);
        productRepository.save(productTofu);
        productRepository.save(productTernera);
        productRepository.save(productSetas);

        productPollo.getMenusAssociated().addAll(List.of(menuVeggie, menuBurguer));
        productRepository.save(productPollo);

        productSetas.getMenusAssociated().addAll(List.of(menuLibanes, menuVeggie, menuBurguer));
        productRepository.save(productSetas);

        productTernera.getMenusAssociated().addAll(List.of(menuBurguer));
        productRepository.save(productTernera);
    }
}