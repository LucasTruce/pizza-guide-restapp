package com.pizzaguideapp.models.components;


import com.pizzaguideapp.models.ingredients.Ingredient;
import com.pizzaguideapp.models.recipes.Recipe;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "components")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Component {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "amount")
    private int amount;

    @ManyToOne
    @JoinColumn(name = "ingredient_id", nullable = false)
    private Ingredient ingredient;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;
}
