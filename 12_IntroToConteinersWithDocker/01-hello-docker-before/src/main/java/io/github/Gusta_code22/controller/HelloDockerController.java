package io.github.Gusta_code22.controller;

import io.github.Gusta_code22.enviroment.InstanceInformationService;
import io.github.Gusta_code22.model.HelloDocker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class HelloDockerController {

    @Autowired
    private InstanceInformationService service;

    @GetMapping("/")
    public String imUpAndRunning(){
        return "{healthy:true}";
    }

    @RequestMapping("/hello-docker")
    public HelloDocker helloDocker(){
        return new HelloDocker(
                "Hello Docker - V1",
                service.retrieveInstanceInfo()
        );
    }

}
