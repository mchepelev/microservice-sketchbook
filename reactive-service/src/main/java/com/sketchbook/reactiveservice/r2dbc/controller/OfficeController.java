package com.sketchbook.reactiveservice.r2dbc.controller;

import com.sketchbook.reactiveservice.r2dbc.entity.Office;
import com.sketchbook.reactiveservice.r2dbc.service.OfficeServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/r2dbc")
public class OfficeController {
    @Autowired
    private OfficeServiceFacade facade;

    @PostMapping("/company/office")
    public Mono<ResponseEntity<Office>> createOfficeWithCompany(@RequestParam String companyName,
                                                                @RequestParam String officeName,
                                                                @RequestParam Integer addressId) {
        return facade.createOffice(officeName, companyName, addressId)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/company/{companyId}/office/{officeId}")
    public Mono<ResponseEntity<Office>> updateOfficeWithCompany(@PathVariable Integer companyId,
                                                                @PathVariable Integer officeId,
                                                                @RequestParam String companyName,
                                                                @RequestParam String officeName,
                                                                @RequestParam Integer addressId) {
        return facade.updateOffice(companyId, officeId, officeName, companyName, addressId)
                .map(ResponseEntity::ok);
    }


}
