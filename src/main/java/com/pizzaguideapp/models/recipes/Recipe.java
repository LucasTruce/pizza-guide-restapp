package com.pizzaguideapp.models.recipes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pizzaguideapp.models.components.Component;
import com.pizzaguideapp.models.media.Media;
import com.pizzaguideapp.models.reviews.Review;
import com.pizzaguideapp.models.steps.Steps;
import com.pizzaguideapp.models.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "recipes", schema = "pizzadb")
@JsonIgnoreProperties({"user"})
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Can't be blank!")
    private String name;

    @NotBlank(message = "Can't be blank!")
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "recipe", orphanRemoval = true)
    private List<Steps> stepsList = new ArrayList<>();

    @OneToMany(mappedBy = "recipe", orphanRemoval = true)
    private List<Media> mediaList = new ArrayList<>();

    @OneToMany(mappedBy = "recipe", orphanRemoval = true)
    private List<Review> reviewList;

    @OneToMany(mappedBy = "recipe", orphanRemoval = true)
    private List<Component> componentList;

}
