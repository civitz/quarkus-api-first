package pro.robertopiva.api;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.jboss.logging.Logger;

import pro.robertopiva.beans.NewPet;
import pro.robertopiva.beans.Pet;

@ApplicationScoped
public class Pets implements PetsApi {

    final static Logger log = Logger.getLogger(Pets.class);

    ConcurrentHashMap<Long, Pet> pets = new ConcurrentHashMap<>();
    AtomicLong ids = new AtomicLong(1);

    @Override
    public Pet addPet(@Valid @NotNull NewPet newPet) {
        log.infov("adding pet {0}", newPet);
        long id = ids.incrementAndGet();
        var created = new Pet().id(id).name(newPet.getName()).tag(newPet.getTag());
        pets.put(id, created);
        log.infov("created {0}", created);
        return created;
    }

    @Override
    public void deletePet(Long id) {
        log.debugv("removing {0}", id);
        var removed = pets.remove(id);
        log.infov("removed id {0} containing {1}", id, removed);
    }

    @Override
    public Pet findPetById(Long id) {
        var found = pets.get(id);
        log.infov("looking for id {0}, found {1}", id, found);
        return found;
    }

    @Override
    public List<Pet> findPets(List<String> tags, Integer limit) {
        var unlimited = limit == null || limit.intValue() < 0;
        log.infov("looking for {0} pets with tags {1}", unlimited
                ? "unlimited"
                : limit.toString(), tags);
        var filtered = pets.values().stream()
                .filter(one -> tags == null || tags.isEmpty() ? true : tags.contains(one.getTag()));

        return unlimited ? filtered.collect(Collectors.toList()) : filtered.limit(limit).collect(Collectors.toList());
    }

}
