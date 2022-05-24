package com.sketchbook.coreservice.db.sync;

import com.sketchbook.coreservice.db.sync.JdbcRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class JdbcRepositoryTest {

    @Autowired
    private JdbcRepository jdbcRepository;

    @Test
    void findCompanyNameById() {
        String name = jdbcRepository.findCompanyNameById(1);

        assertEquals("Manor Farm", name);
    }
}