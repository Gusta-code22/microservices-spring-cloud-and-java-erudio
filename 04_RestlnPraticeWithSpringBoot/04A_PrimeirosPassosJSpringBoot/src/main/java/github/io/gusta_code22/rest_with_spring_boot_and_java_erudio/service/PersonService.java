package github.io.gusta_code22.rest_with_spring_boot_and_java_erudio.service;

import github.io.gusta_code22.rest_with_spring_boot_and_java_erudio.Exception.ResourceNotFoundException;
import github.io.gusta_code22.rest_with_spring_boot_and_java_erudio.model.Person;
import github.io.gusta_code22.rest_with_spring_boot_and_java_erudio.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository repository;

    private Logger logger = Logger.getLogger(PersonService.class.getName());

    public Person findById(Long id){
        logger.info("Procurando uma pessoa!");

        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No Record found for this id"));
    }

    public Person create(Person person){
        logger.info("Criando uma pessoa");
        return repository.save(person);
    }

    public Person update(Person person){
        logger.info("updating uma pessoa");

        Person entity = repository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No Record found for this id"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return repository.save(person);
    }
    public void delete(Long id){
        logger.info("deleting uma pessoa");
        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No Record found for this id"));

        repository.delete(entity);
    }

    public List<Person> findAll(){
        logger.info("Procurando todas as pessoas!");
        return repository.findAll();
    }

}
