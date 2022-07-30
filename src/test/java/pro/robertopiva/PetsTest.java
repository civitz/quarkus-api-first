package pro.robertopiva;

import static org.assertj.core.api.Assertions.assertThat;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import pro.robertopiva.api.PetsApi;
import pro.robertopiva.beans.NewPet;
import pro.robertopiva.beans.Pet;

@QuarkusTest
public class PetsTest {

    @Inject
    PetsApi api;

    @Test
    void shouldCreateAndThenFindPet() {
        Pet created = api.addPet(new NewPet().name("fido").tag("f"));
        assertThat(created)
                .as("Created pet should have id")
                .hasNoNullFieldsOrProperties();
        Pet byId = api.findPetById(created.getId());
        assertThat(byId)
                .as("Created and by id should be equal").isEqualTo(created);
    }

    @Test
    void shouldCreateAndListMultiplePets() {
        Pet dog = api.addPet(new NewPet().name("fido").tag("dog"));
        Pet cat = api.addPet(new NewPet().name("fluffy").tag("cat"));
        var all = api.findPets(null, null);
        assertThat(all)
            .as("listing without filters should return all pets")
            .contains(dog,cat);
    }
}
