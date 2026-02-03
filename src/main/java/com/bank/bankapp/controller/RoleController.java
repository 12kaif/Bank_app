package com.bank.bankapp.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.bank.bankapp.repository.RoleRepository;
import com.bank.bankapp.model.Role;
import java.util.List;



@RestController
@RequestMapping("/api/roles")
public class RoleController {

    private final RoleRepository repo;

    public RoleController(RoleRepository repo) {
        this.repo = repo;
    }

    @PostMapping
    public Role create(@RequestBody Role role) {
        return repo.save(role);
    }

    @GetMapping
    public List<Role> getAll() {
        return repo.findAll();
    }
}
