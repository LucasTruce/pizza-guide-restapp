package com.pizzaguideapp.models.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pizzaguideapp.models.recipes.Recipe;
import com.pizzaguideapp.models.reviews.Review;
import com.pizzaguideapp.models.role.Role;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Entity
@Getter
@Setter
@Table(name = "users", schema = "pizzadb")
@JsonIgnoreProperties({"recipeList"})
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    @NotBlank(message = "Can't be blank!")
    @NotNull
    @Size(min = 2, max = 14)
    private String username;

    @Column(name = "email")
    @NotBlank(message = "Can't be blank!")
    @Email(message = "Must be in email format!")
    private String email;

    @Column(name = "password")
    @NotBlank(message = "Can't be blank!")
    @NotNull
    private String password;

    private boolean enabled;

    @Column(name = "account_locked")
    private boolean accountLocked;

    @Column(name = "account_expired")
    private boolean accountExpired;

    @Column(name = "credentials_expired")
    private boolean credentialsExpired;


    //connections

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", schema = "pizzadb", joinColumns = {
            @JoinColumn(name = "user_id", referencedColumnName = "id")}, inverseJoinColumns = {
            @JoinColumn(name = "role_id", referencedColumnName = "id") })
    private List<Role> roles;

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private List<Recipe> recipeList = new ArrayList<>();

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private List<Review> reviewList;

    //Methods
    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !accountExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !accountLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !credentialsExpired;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        roles.forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName().name()));
            role.getPrivileges().stream()
                    .map(p -> new SimpleGrantedAuthority(p.getName()))
                    .forEach(authorities::add);
        });

        return authorities;
    }
}
