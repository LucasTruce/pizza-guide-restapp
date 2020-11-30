package com.pizzaguideapp.models.components.dto;

import com.pizzaguideapp.models.components.Component;
import com.pizzaguideapp.models.ingredients.dto.IngredientConverter;

import java.util.List;
import java.util.stream.Collectors;

public class ComponentConverter {
    public ComponentDto map(Component component) {
        IngredientConverter ingredientConverter = new IngredientConverter();

        return new ComponentDto(
                component.getAmount(),
                ingredientConverter.map(component.getIngredient())
        );
    }

    public Component map(ComponentDto componentDto) {
        Component component = new Component();
        IngredientConverter ingredientConverter = new IngredientConverter();
        component.setAmount(componentDto.getAmount());
        component.setIngredient(ingredientConverter.map(componentDto.getIngredients()));
        return component;
    }


    public List<ComponentDto> map(List<Component> entityObjects){
        return entityObjects.stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    public List<Component> mapDtos(List<ComponentDto> componentDtos){
        return componentDtos.stream()
                .map(this::map)
                .collect(Collectors.toList());
    }
}
