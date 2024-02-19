package com.workintech.s17g1.controller;

import com.workintech.s17g1.entity.Animal;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/workintech/animal")
public class AnimalController {
    private Map<Integer, Animal> animalMap;

    public AnimalController() {
    }

    @PostConstruct
    public void init(){
        animalMap=new HashMap<>();
        animalMap.put(1,new Animal(1,"kedi"));
        System.out.println(animalMap.get(1).getName() );
    }

    @GetMapping
    public List<Animal> getAnimals(){
        System.out.println("---- get all triggered----");
        return new ArrayList<>(animalMap.values());
    }
    @GetMapping(path="/{id}")
    public Animal get(@PathVariable("id") Integer id){
        System.out.println("------get by id triggered ");
        return animalMap.get(id);
    }
    @PostMapping
    public void addAnimal (@RequestBody Animal animal){
        animalMap.put(animal.getId(), animal);
    }
    @PutMapping("/{id}")
    public void updateAnimal(@PathVariable int id, @RequestBody Animal updatedAnimal) {
        if (animalMap.containsKey(id)) {
            updatedAnimal.setId(id);
            animalMap.put(id, updatedAnimal);
        }
    }
    @DeleteMapping("/{id}")
    public void deleteAnimal(@PathVariable int id) {
        animalMap.remove(id);
    }
}
