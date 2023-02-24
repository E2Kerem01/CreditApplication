package com.patika.definexproject.service;

import com.patika.definexproject.model.Credit;
import com.patika.definexproject.model.User;
import com.patika.definexproject.repository.CreditRepository;
import com.patika.definexproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CreditService {

    private double Amount;
    public final int creditMultiplier = 4;
    @Autowired
    CreditRepository creditRepository;
    private final UserRepository userRepository;

    public CreditService(CreditRepository creditRepository,
                         UserRepository userRepository) {
        this.creditRepository = creditRepository;
        this.userRepository = userRepository;
    }

    public Page<Credit> getPagesOfCredit(Pageable pageable) {
        return creditRepository.findAll(pageable);
    }

    public void save(Credit credit) {
        Optional<User> user = userRepository.findById(credit.getUser().getId());

        if (!user.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND," Böyle bir kullanıcı bulumamaktadır.");
        }
        else {
            credit.setResult(true);

            if (credit.getCreditRating()<500){
                credit.setResult(false);
                creditRepository.save(credit);
                throw new ResponseStatusException(HttpStatus.OK," " +
                        "Kredi Puanı 500'ün altında olduğu için başvurunuz kabul edilememektedir.");
            }

            if ((500<credit.getCreditRating() && credit.getCreditRating()<1000) && credit.getMonthlyIncome()<5000) {
                if (credit.getGuarantee()>0){
                    Amount = 10000+(credit.getGuarantee()/10);
                    creditRepository.save(credit);
                    throw new ResponseStatusException(HttpStatus.OK, + Amount + " Kredi limitiniz bulunmaktadır.");
                }else {
                    creditRepository.save(credit);
                    throw new ResponseStatusException(HttpStatus.OK, + Amount + " Kredi limitiniz bulunmaktadır.");
                }

            }creditRepository.save(credit);

            if (((500<credit.getCreditRating() && credit.getCreditRating()<1000)
                    && (credit.getMonthlyIncome()>5000) && (credit.getMonthlyIncome()<10000))) {
                if (credit.getGuarantee()>0){
                    Amount = 20000 + (credit.getGuarantee()/5);
                    throw new ResponseStatusException(HttpStatus.OK, + Amount + " Kredi limitiniz bulunmaktadır.");
                }else {
                    Amount = 20000;
                    throw new ResponseStatusException(HttpStatus.OK, + Amount + " Kredi limitiniz bulunmaktadır.");
                }
            }creditRepository.save(credit);

            if (((500<credit.getCreditRating() && credit.getCreditRating()<1000)
                    && (credit.getMonthlyIncome()>10000))) {
                if (credit.getGuarantee()>0){
                    Amount = ((credit.getMonthlyIncome() * creditMultiplier/2)
                            + credit.getGuarantee()/4);
                    throw new ResponseStatusException(HttpStatus.OK, + Amount + " Kredi limitiniz bulunmaktadır.");
                }else {
                    Amount = (credit.getMonthlyIncome() * creditMultiplier/2);
                    throw new ResponseStatusException(HttpStatus.OK, + Amount + " Kredi limitiniz bulunmaktadır.");
                }
            }creditRepository.save(credit);
            if (credit.getCreditRating()>=1000) {

                if(credit.getGuarantee()>0){
                    Amount = ((credit.getMonthlyIncome() * creditMultiplier)+credit.getGuarantee()/2);
                    throw new ResponseStatusException(HttpStatus.OK, + Amount + " Kredi limitiniz bulunmaktadır.");
                }else {
                    Amount = credit.getMonthlyIncome() * creditMultiplier;
                    throw new ResponseStatusException(HttpStatus.OK, + Amount + " Kredi limitiniz bulunmaktadır.");
                }
            } creditRepository.save(credit);
        }
    }

    public List<Credit> getCreditsForId(String tcIdentificationNumber /*, Date birthDate*/) {

        return creditRepository.findByTcIdentificationNumber(tcIdentificationNumber);
    }
}
