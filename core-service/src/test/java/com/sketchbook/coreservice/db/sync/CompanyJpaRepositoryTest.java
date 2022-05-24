package com.sketchbook.coreservice.db.sync;

import com.sketchbook.coreservice.db.sync.entity.Company;
import com.sketchbook.coreservice.db.sync.CompanyJpaRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class CompanyJpaRepositoryTest {

    @Autowired
    private CompanyJpaRepository repository;

    @Test
    void findCompanyById() {
//        Company company = repository.findCompanyById(1);
        Company company = repository.findAll().get(0);

        assertEquals("Manor Farm", company.getName());
    }
}