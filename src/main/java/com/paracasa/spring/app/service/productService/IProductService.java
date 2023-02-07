package com.paracasa.spring.app.service.productService;
import com.paracasa.spring.app.model.Product;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    List<Product> findAll();
    Optional<Product> findById(Long id);
    Product create(Product Product);
    Product update(Product Product);
    void delete(Long id);
}