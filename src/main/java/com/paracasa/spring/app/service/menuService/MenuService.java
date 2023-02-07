package com.paracasa.spring.app.service.menuService;

import com.paracasa.spring.app.model.Menu;
import com.paracasa.spring.app.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MenuService implements IMenuService {
    @Autowired
    private MenuRepository menuRepository;

    public List<Menu> findAll(){
        return this.menuRepository.findAll();
    }

    @Override
    public Optional<Menu> findById(Long id) {
        return menuRepository.findById(id);
    }

    @Override
    public Menu create(Menu menu) {
        return menuRepository.save(menu);
    }

    @Override
    public Menu update(Menu menu) {
        Menu currentMenu = menuRepository.findById(menu.getId()).get();
        currentMenu.setPrice(menu.getPrice());
        currentMenu.setName(menu.getName());
        return menuRepository.save(currentMenu);
    }

    @Override
    public void delete(Long id) {
        Optional<Menu> menu = menuRepository.findById(id);
        System.out.println(menu);
        menuRepository.deleteById(id);
    }
}
