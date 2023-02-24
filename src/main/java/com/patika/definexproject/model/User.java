package com.patika.definexproject.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import com.patika.definexproject.shared.Views;
import com.patika.definexproject.validators.UniqueUsername;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Set;


@Data
@Entity
@Table(name = "users")
public class User implements UserDetails {
    
    @Id
    @GeneratedValue
    private Long id;
    @NotNull(message = "{academicwebsite.constraints.username.NotNull.message}")
    @Size(min = 4, max = 255)
    @UniqueUsername
    @JsonView(Views.Base.class)
    private String username;
    @NotNull
    @Size(min = 4, max = 255)
    @Column(unique = true)
    @JsonView(Views.Base.class)
    private String email;
    @NotNull
    @Size(min = 8, max = 255)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$", message = "{academicwebsite.constraints.password.Pattern.message}")
    @JsonView(Views.Sensitive.class)
    private String password;

    @JsonBackReference
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private Set<Credit> credits;


    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", displayName='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.createAuthorityList("Role_user");
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
