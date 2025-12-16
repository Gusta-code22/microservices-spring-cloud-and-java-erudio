package github.io.gusta_code22.rest_with_spring_boot_and_java_erudio.model;


import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
@Table(name = "Person")
public class Person implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "first_name", nullable = false, length = 80)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column( nullable = false, length = 100)
    private String address;

    @Column(nullable = false, length = 10)
    private String gender;
}
