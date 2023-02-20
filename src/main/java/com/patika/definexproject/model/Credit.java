package com.patika.definexproject.model;


import com.fasterxml.jackson.annotation.JsonView;
import com.patika.definexproject.shared.Views;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@Entity
@Table(name = "credits")
public class Credit {

    @Id
    private Long creditId;

    @NotNull
    @Size(min = 11, max = 11)
    @JsonView(Views.Base.class)
    private String tcIdentificationNumber;

    @NotNull
    @Size(min = 4, max = 255)
    @JsonView(Views.Base.class)
    private String Name;

    @NotNull
    @JsonView(Views.Base.class)
    private double monthlyIncome;

    @NotNull
    @Size(max = 10,min = 10)
    @JsonView(Views.Base.class)
    private String phoneNumber;

    @NotNull
    @DateTimeFormat
    private Date birthDate;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "users_id", referencedColumnName = "Id")
    private User user;

}
