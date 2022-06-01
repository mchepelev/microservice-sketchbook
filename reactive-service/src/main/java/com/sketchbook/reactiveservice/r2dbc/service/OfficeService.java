package com.sketchbook.reactiveservice.r2dbc.service;

import com.sketchbook.reactiveservice.r2dbc.entity.Company;
import com.sketchbook.reactiveservice.r2dbc.entity.Office;
import com.sketchbook.reactiveservice.r2dbc.repository.CompanyRepository;
import com.sketchbook.reactiveservice.r2dbc.repository.OfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
public class OfficeService {

    @Autowired
    private OfficeRepository officeRepository;
    @Autowired
    private CompanyRepository companyRepository;

    @Transactional
    public Mono<Office> createOffice(String officeName, String companyName, Integer addressId) {
        return companyRepository.save(new Company()
                        .setName(companyName))
                .map(c -> new Office()
                        .setName(officeName)
                        .setCompanyId(c.getId())
                        .setAddressId(addressId))
                .flatMap(office -> officeRepository.save(office));
    }

    @Transactional
    public Mono<Office> updateOffice(Integer companyId, Integer officeId, String officeName, String companyName, Integer addressId) {
        return companyRepository.save(new Company()
                        .setId(companyId)
                        .setName(companyName))
                .map(c -> new Office()
                        .setId(officeId)
                        .setName(officeName)
                        .setCompanyId(companyId)
                        .setAddressId(addressId))
                .flatMap(office -> officeRepository.save(office));
    }

}
