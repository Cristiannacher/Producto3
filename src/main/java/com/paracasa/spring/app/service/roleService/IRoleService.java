package com.paracasa.spring.app.service.roleService;

import com.paracasa.spring.app.model.Menu;
import com.paracasa.spring.app.model.Role;

import java.util.List;
import java.util.Optional;

public interface IRoleService {
    List<Role> findAll();
    Optional<Role> findById(Long id);
    Role create(Role menu);
    Role update(Role menu);
    void delete(Long id);
}
