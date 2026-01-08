package github.io.gusta_code22.controller;



import github.io.gusta_code22.config.GreetingConfiguration;
import github.io.gusta_code22.model.Greeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController

public class GreedingController {

    private static final String template = "%s, %s!";
    private static final AtomicLong counter = new AtomicLong();

    @Autowired
    private GreetingConfiguration configuration;

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "") String name){
        if (name.isEmpty()){
            name = configuration.defaultValue();
        }
        return new Greeting(counter.incrementAndGet(), String.format(
                template,
                configuration.greeting(),
                name));

    }
}