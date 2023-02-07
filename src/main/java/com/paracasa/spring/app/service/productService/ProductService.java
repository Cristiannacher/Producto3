package com.paracasa.spring.app.service.productService;

import com.paracasa.spring.app.model.Product;
import com.paracasa.spring.app.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll() {
        return this.productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product create(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product update(Product products) {
        Product currentProducts= productRepository.findById(products.getId()).get();
        currentProducts.setPrice(products.getPrice());
        currentProducts.setName(products.getName());
        return productRepository.save(currentProducts);
    }

    @Override
    public void delete(Long id) {
        Optional<Product> product=productRepository.findById(id);
        System.out.println(product);
        productRepository.deleteById(id);

    }

}
