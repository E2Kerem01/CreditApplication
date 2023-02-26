package com.patika.definexproject.service;

import com.patika.definexproject.model.Credit;
import com.patika.definexproject.model.User;
import com.patika.definexproject.repository.CreditRepository;
import com.patika.definexproject.repository.UserRepository;
import com.patika.definexproject.utils.CreditMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;

@Service
public class CreditService {

    private double Amount;
    public final int creditMultiplier = 4;

    private final CreditRepository creditRepository;
    private final UserRepository userRepository;
    private final SmsService smsService;
    private final CreditMessage creditMessage;



    public CreditService(CreditRepository creditRepository,
                         UserRepository userRepository, SmsService smsService, CreditMessage creditMessage) {
        this.creditRepository = creditRepository;
        this.userRepository = userRepository;
        this.smsService = smsService;
        this.creditMessage = creditMessage;
    }

    public Page<Credit> getPagesOfCredit(Pageable pageable) {                                                                // Tüm kredilerin dönüldüğü servis
        return creditRepository.findAll(pageable);
    }

    public void save(Credit credit) throws IOException {                                                                       // Kredi koşullarına göre cevabın dönüldüğü servis
        User user = userRepository.findByUsername(credit.getUsername());

        if (user==null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND," Böyle bir kullanıcı bulumamaktadır.");
        }
        else {
            credit.setResult(true);

            if (credit.getCreditRating()<500){
                credit.setResult(false);
                creditRepository.save(credit);
                smsService.sendSms(credit.getPhoneNumber(),creditMessage.getMessage1());
                throw new ResponseStatusException(HttpStatus.OK, creditMessage.getMessage1());
            }

            if ((500<credit.getCreditRating() && credit.getCreditRating()<1000) && credit.getMonthlyIncome()<5000) {
                if (credit.getGuarantee()>0){
                    Amount = 10000+(credit.getGuarantee()/10);
                    creditRepository.save(credit);
                    smsService.sendSms(credit.getPhoneNumber(),Amount + creditMessage.getMessage2());
                    throw new ResponseStatusException(HttpStatus.OK, + Amount + creditMessage.getMessage2());
                }else {
                    creditRepository.save(credit);
                    smsService.sendSms(credit.getPhoneNumber(),Amount + creditMessage.getMessage2());
                    throw new ResponseStatusException(HttpStatus.OK, + Amount + creditMessage.getMessage2());
                }

            }creditRepository.save(credit);

            if (((500<credit.getCreditRating() && credit.getCreditRating()<1000) && (credit.getMonthlyIncome()>5000) && (credit.getMonthlyIncome()<10000))) {
                if (credit.getGuarantee()>0){
                    Amount = 20000 + (credit.getGuarantee()/5);
                    smsService.sendSms(credit.getPhoneNumber(),Amount + creditMessage.getMessage2());
                    throw new ResponseStatusException(HttpStatus.OK, + Amount + creditMessage.getMessage2());
                }else {
                    Amount = 20000;
                    smsService.sendSms(credit.getPhoneNumber(),Amount + creditMessage.getMessage2());
                    throw new ResponseStatusException(HttpStatus.OK, + Amount + creditMessage.getMessage2());
                }
            }creditRepository.save(credit);

            if (((500<credit.getCreditRating() && credit.getCreditRating()<1000) && (credit.getMonthlyIncome()>10000))) {
                if (credit.getGuarantee()>0){
                    Amount = ((credit.getMonthlyIncome() * creditMultiplier/2) + credit.getGuarantee()/4);
                    smsService.sendSms(credit.getPhoneNumber(),Amount + creditMessage.getMessage2());
                    throw new ResponseStatusException(HttpStatus.OK, + Amount + creditMessage.getMessage2());
                }else {
                    Amount = (credit.getMonthlyIncome() * creditMultiplier/2);
                    smsService.sendSms(credit.getPhoneNumber(),Amount + creditMessage.getMessage2());
                    throw new ResponseStatusException(HttpStatus.OK, + Amount + creditMessage.getMessage2());
                }
            }creditRepository.save(credit);

            if (credit.getCreditRating()>=1000) {

                if(credit.getGuarantee()>0){
                    Amount = ((credit.getMonthlyIncome() * creditMultiplier)+credit.getGuarantee()/2);
                    smsService.sendSms(credit.getPhoneNumber(),Amount + creditMessage.getMessage2());
                    throw new ResponseStatusException(HttpStatus.OK, + Amount + creditMessage.getMessage2());
                }else {
                    Amount = credit.getMonthlyIncome() * creditMultiplier;
                    smsService.sendSms(credit.getPhoneNumber(),Amount + creditMessage.getMessage2());
                    throw new ResponseStatusException(HttpStatus.OK, + Amount + creditMessage.getMessage2());
                }
            }creditRepository.save(credit);
        }
    }

    public List<Credit> getCreditsForId(String tcIdentificationNumber /*, Date birthDate*/) {                               // TCKN numarasına göre kredileri dönen servis
        return creditRepository.findByTcIdentificationNumberAndResultTrue(tcIdentificationNumber);
    }
}
