package com.alayon.shopme.site.admin.user;

import com.alayon.shopme.common.entity.Role;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class RoleRepositoryTest {

    @Autowired
    private RoleRepository repository;

    @Test
    public void createNewRole_HappyPath(){
        Role adminRole = new Role("Admin", "Manage Everything!");
        Role savedRole = repository.save(adminRole);

        assertThat(savedRole.getId()).isGreaterThan(0);
    }

    @Test
    public void createAllRoles_HappyPath(){
        Role salesPersonRole = new Role("Salesperson", "Manage product price, etc...");
        Role editorRole = new Role("Editor", "Manage categories, brands, products, etc...");
        Role shipperRole = new Role("Shipper", "View Products, view orders, etc...");
        Role assistantRole = new Role("Assistant", "Manage questions and reviews");

        Iterable<Role> savedRoles = repository.saveAll(List.of(salesPersonRole, editorRole, shipperRole, assistantRole));
        assertThat(savedRoles).allMatch(role -> role.getId() > 0);
    }
}
