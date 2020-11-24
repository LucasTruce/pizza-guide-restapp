package com.pizzaguideapp.models.steps;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pizzaguideapp.models.recipes.Recipe;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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

    private String name;

    private String description;

    private Time time;

    private float temperature;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;
}
