package com.paracasa.spring.app.service.menuService;

import com.paracasa.spring.app.model.Menu;

import java.util.List;
import java.util.Optional;

public interface IMenuService {
    List<Menu> findAll();
    Optional<Menu> findById(Long id);
    Menu create(Menu menu);
    Menu update(Menu menu);
    void delete(Long id);
}
