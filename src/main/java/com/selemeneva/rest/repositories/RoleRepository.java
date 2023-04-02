package com.selemeneva.rest.repositories;

import com.selemeneva.rest.model.Role;
import java.util.Set;


public interface RoleRepository  {
    void save(Role role);
    Set<Role> findAll();
}
