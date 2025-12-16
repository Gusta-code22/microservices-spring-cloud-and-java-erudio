package github.io.gusta_code22.rest_with_spring_boot_and_java_erudio.repository;

import github.io.gusta_code22.rest_with_spring_boot_and_java_erudio.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person,Long > {

}
