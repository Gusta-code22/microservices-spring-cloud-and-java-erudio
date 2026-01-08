package github.io.gusta_code22.rest_with_spring_boot_and_java_erudio.controller;

import github.io.gusta_code22.rest_with_spring_boot_and_java_erudio.model.Person;
import github.io.gusta_code22.rest_with_spring_boot_and_java_erudio.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/person")
public class PersonController {

    private final PersonService service;

    private static final AtomicLong counter = new AtomicLong();

    @GetMapping("/{id}")
    public Person findById(@PathVariable Long id){
        return service.findById(id);

    }

    @GetMapping
    public List<Person> findAll(){
        return service.findAll();
    }

    @PostMapping
    public Person create(@RequestBody Person person){
        return service.create(person);
    }

    @PutMapping
    public Person update(@RequestBody Person person){
        return service.update(person);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
