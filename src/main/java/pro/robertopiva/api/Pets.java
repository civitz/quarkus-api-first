package pro.robertopiva.api;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import pro.robertopiva.beans.NewPet;
import pro.robertopiva.beans.Pet;

public class Pets implements PetsApi {

    @Override
    public Pet addPet(@Valid @NotNull NewPet newPet) {
        // TODO Auto-generated method stub
        System.out.println("add pet");
        return null;
    }

    @Override
    public void deletePet(Long id) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Pet findPetById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Pet> findPets(List<String> tags, Integer limit) {
        // TODO Auto-generated method stub
        System.out.println("find pets");
        return null;
    }
    
}
