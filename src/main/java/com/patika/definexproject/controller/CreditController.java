package com.patika.definexproject.controller;


import com.patika.definexproject.model.Credit;
import com.patika.definexproject.service.CreditService;
import com.patika.definexproject.service.SmsService;
import com.patika.definexproject.shared.GenericResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class CreditController {


    private final CreditService creditService;
    private final SmsService smsService;

    public CreditController(CreditService creditService, SmsService smsService) {
        this.creditService = creditService;
        this.smsService = smsService;
    }


    @PostMapping("calculate")
    public GenericResponse createCreditApplication(@Valid @RequestBody Credit credit) throws IOException {
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
    public List<Credit> getCreditsForId(@PathVariable String tcIdentificationNumber /*, @PathVariable @DateTimeFormat(pattern="yyyy-MM-dd") Date birthDate*/){
        return creditService.getCreditsForId(tcIdentificationNumber);
    }
}
