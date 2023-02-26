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

    @NotNull(message = "IdentificationNumber can not be null...")
    @JsonView(Views.Base.class)
    @UniqueUsername
    private String tcIdentificationNumber;

    @NotNull(message = "Name can not be null...")
    @Size(min = 4, max = 255)
    @JsonView(Views.Base.class)
    private String Name;

    @NotNull(message = "Monthly Income can not be null...")
    @JsonView(Views.Base.class)
    private double monthlyIncome;

    @NotNull(message = "Phone Number can not be null...")
    @JsonView(Views.Base.class)
    private String phoneNumber;

    @NotNull(message = "BirthDate can not be null...")
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

    @JsonView(Views.Base.class)
    private String username;
    @ManyToOne
    private User user;

}
