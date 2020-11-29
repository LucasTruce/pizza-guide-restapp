package com.pizzaguideapp.models.components;

import com.pizzaguideapp.models.components.dto.ComponentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ComponentController {

    private final ComponentService componentService;

    @GetMapping("/recipes/{id}/components")
    public ResponseEntity<List<ComponentDto>> getComponentsForRecipe(@PathVariable(name = "id") Long id){
        return new ResponseEntity<>(componentService.getComponentsForRecipe(id), HttpStatus.OK);
    }


}
