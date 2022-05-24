package com.sketchbook.coreservice.db.sync;

import com.sketchbook.coreservice.db.sync.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyJpaRepository extends JpaRepository<Company, Integer> {

    Company findCompanyById(Integer id);

    List<Company> findAll();
}
