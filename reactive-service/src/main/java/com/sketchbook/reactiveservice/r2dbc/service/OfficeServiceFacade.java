package com.sketchbook.reactiveservice.r2dbc.service;

import com.sketchbook.reactiveservice.r2dbc.entity.Office;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class OfficeServiceFacade {
    @Autowired
    private OfficeService officeService;

    public Mono<Office> createOffice(String officeName, String companyName, Integer addressId) {
        return officeService.createOffice(officeName, companyName, addressId);
    }

    public Mono<Office> updateOffice(Integer companyId, Integer officeId, String officeName, String companyName, Integer addressId) {
        return officeService.updateOffice(companyId, officeId, officeName, companyName, addressId);
    }
}
