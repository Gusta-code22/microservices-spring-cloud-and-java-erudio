package github.io.gusta_code22.rest_with_spring_boot_and_java_erudio.controller;

import github.io.gusta_code22.rest_with_spring_boot_and_java_erudio.model.Greeting;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController

public class GreedingController {

    private static final String template = "hello, %s!";
    private static final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "word") String name){
        return new Greeting(counter.incrementAndGet(), String.format(template, name));

    }
}
