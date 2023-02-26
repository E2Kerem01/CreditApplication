package com.patika.definexproject.utils;

import lombok.Data;
import org.springframework.stereotype.Service;

@Data
@Service
public class CreditMessage {

    private final String message1 = " Kredi Puani 500\'ün altinda oldugu icin basvurunuz kabul edilememektedir.";
    private final String message2 = " Kredi limitiniz bulunmaktadir.";

}
