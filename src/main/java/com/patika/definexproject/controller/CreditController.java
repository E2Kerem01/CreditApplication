package com.patika.definexproject.controller;


import com.patika.definexproject.model.Credit;
import com.patika.definexproject.service.CreditService;
import com.patika.definexproject.shared.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class CreditController {

    @Autowired
    CreditService creditService;


    @PostMapping("calculate")
    @ResponseStatus(HttpStatus.OK)
    public GenericResponse createCreditApplication(@Valid @RequestBody Credit credit){
        creditService.save(credit);
        return new GenericResponse("Credit Application");
    }

    @GetMapping("listCredits")
    @ResponseStatus(HttpStatus.OK)
    public Page<Credit> listCreditApplication(Pageable pageable){
        return creditService.getPagesOfCredit(pageable);
    }

    @GetMapping("creditsForId/{tcIdentificationNumber}/{birthDate}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<Credit> getCreditsForId(@PathVariable String tcIdentificationNumber/*, @PathVariable @DateTimeFormat(pattern="yyyy-MM-dd") Date birthDate*/){
        return creditService.getCreditsForId(tcIdentificationNumber);
    }
}
