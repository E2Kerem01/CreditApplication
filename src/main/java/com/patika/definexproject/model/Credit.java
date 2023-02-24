package com.patika.definexproject.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import com.patika.definexproject.shared.Views;
import com.patika.definexproject.validators.UniqueUsername;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@Entity
@Table(name = "credit")
public class Credit {

    @Id
    @GeneratedValue
    private Long creditId;

    @NotNull
    @JsonView(Views.Base.class)
    @UniqueUsername
    private String tcIdentificationNumber;

    @NotNull
    @Size(min = 4, max = 255)
    @JsonView(Views.Base.class)
    private String Name;

    @NotNull
    @JsonView(Views.Base.class)
    private double monthlyIncome;

    @NotNull
    @JsonView(Views.Base.class)
    private String phoneNumber;

    @NotNull
    @DateTimeFormat
    @JsonView(Views.Base.class)
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date birthDate;

    @NotNull
    @JsonView(Views.Base.class)
    private double guarantee;

    @JsonView(Views.Base.class)
    private int creditRating;

    @Column(name = "kredi_sonuc")
    private boolean result;

    @ManyToOne
    private User user;

}
