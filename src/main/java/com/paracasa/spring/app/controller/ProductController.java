package com.paracasa.spring.app.controller;


import com.paracasa.spring.app.model.Product;
import com.paracasa.spring.app.service.productService.ProductService;
import com.paracasa.spring.app.service.roleService.RoleService;
import com.paracasa.spring.app.service.usuarioService.IUsuarioService;
import com.paracasa.spring.app.utils.CheckSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.Optional;


@Controller
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private IUsuarioService userService;
    @Autowired
    private RoleService roleService;

    @GetMapping("/product")
    public String products(Model model, Authentication auth, HttpSession session){
        this.checkSession(auth, session,
                userService, roleService);
        model.addAttribute("product",productService.findAll());
        model.addAttribute("currentPage", "product");
        return "product";
    }

    @GetMapping("/product/{id}")
    public String getProductDetail(Model model, @PathVariable Long id, Authentication auth, HttpSession session) throws Exception {
        this.checkSession(auth, session,
                userService, roleService);
        Optional<Product> product =
                Optional.ofNullable(productService.findById(id).orElseThrow(() -> new Exception("Product" + id + " not found")));
        if(product.isPresent()) {
            model.addAttribute("product", product.get());
            model.addAttribute("currentPage", "product");
        }
        return "edit_product";
    }

    @PostMapping("/product/update/{id}")
    public String updateProduct(@PathVariable("id") Long id,
                                @ModelAttribute("product") Product productDetails, Authentication auth, HttpSession session) throws Exception {
        this.checkSession(auth, session,
                userService, roleService);
        Optional<Product> product =
                Optional.ofNullable(productService.findById(id).orElseThrow(() -> new Exception("Product" + id + " not found")));
        if(product.isPresent()){
            product.get().setName(productDetails.getName());
            product.get().setPrice(productDetails.getPrice());
            productService.update(product.get());
        }
        return "redirect:/product/" + id;
    }

    @GetMapping("/product/create")
    public String createProduct(Model model, Authentication auth, HttpSession session) {
        this.checkSession(auth, session,
                userService, roleService);
        model.addAttribute("product", new Product());
        model.addAttribute("currentPage", "product");
        return "create_product";
    }

    @PostMapping("/product/save")
    public String newMenu(Model model, @ModelAttribute("product") Product product) {
        productService.create(product);
        return "redirect:/product";
    }

    private void checkSession(Authentication auth, HttpSession session,
                              IUsuarioService userService,
                              RoleService roleService){
        CheckSession currentSession = new CheckSession(auth, session,
                userService, roleService);
        currentSession.validate();
    }

}