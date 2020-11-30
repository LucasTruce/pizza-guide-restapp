package com.pizzaguideapp.models.steps;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pizzaguideapp.models.recipes.Recipe;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Time;

@Entity
@Table(name = "steps", schema = "pizzadb")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties({"recipe"})
public class Steps {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Can't be blank!")
    private String name;

    @NotBlank(message = "Can't be blank!")
    private String description;

    private Time time;

    private float temperature;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;
}
