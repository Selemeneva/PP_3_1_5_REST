package com.selemeneva.rest.service;

import com.selemeneva.rest.model.Role;
import com.selemeneva.rest.repositories.RoleRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Lazy
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Transactional
    @Override
    public void add(Role role) {
        roleRepository.save(role);
    }

}


