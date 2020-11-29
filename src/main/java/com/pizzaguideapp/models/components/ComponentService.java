package com.pizzaguideapp.models.components;

import com.pizzaguideapp.models.components.dto.ComponentConverter;
import com.pizzaguideapp.models.components.dto.ComponentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComponentService {

    private final ComponentConverter componentConverter;
    private final ComponentRepository componentRepository;

    @Autowired
    public ComponentService(ComponentRepository componentRepository) {
        this.componentRepository = componentRepository;
        this.componentConverter = new ComponentConverter();
    }

    public List<ComponentDto> getComponentsForRecipe(Long recipeId){
        return componentConverter.map(componentRepository.findAllByRecipeId(recipeId));
    }


}
