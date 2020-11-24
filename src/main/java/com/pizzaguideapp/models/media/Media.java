package com.pizzaguideapp.models.media;

import com.pizzaguideapp.models.recipes.Recipe;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "media", schema = "pizzadb")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Media {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String link;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;
}
