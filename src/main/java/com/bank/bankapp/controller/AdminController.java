package com.bank.bankapp.controller;

import com.bank.bankapp.model.Role;
import com.bank.bankapp.repository.RoleRepository;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final RoleRepository roleRepo;

    public AdminController(RoleRepository roleRepo) {
        this.roleRepo = roleRepo;
    }

    @PostMapping("/role")
    public Role createRole(@RequestBody Role role) {
        return roleRepo.save(role);
    }

    @GetMapping("/roles")
    public List<Role> getRoles() {
        return roleRepo.findAll();
    }
}
