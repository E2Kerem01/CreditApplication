package com.patika.definexproject.repository;

import com.patika.definexproject.model.Credit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface CreditRepository extends JpaRepository<Credit, Long> {



        List<Credit> findAllByTcIdentificationNumber(String tcIdentificationNumber);
        Credit findByUser_Id(Long user_id);
        List<Credit> findByTcIdentificationNumberAndResultTrue(String tcIdentificationNumber);
        Credit findAllByTcIdentificationNumberAndResult(String tcIdentificationNumber, boolean result);


}