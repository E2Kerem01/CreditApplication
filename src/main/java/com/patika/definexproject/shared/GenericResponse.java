package com.patika.definexproject.shared;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GenericResponse {

    private String message;

// Data kullandığımız için getter ve setter'ları yazmamıza gerek yok.
    /*public String getMessage(){
        return message;
    }

    public void setMessage(String message){
        this.message = message;
    }*/
}
