package io.github.Gusta_code22.controller;

import io.github.Gusta_code22.enviroment.InstanceInformationService;
import io.github.Gusta_code22.model.HelloDocker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class HelloDockerController {

    Logger logger = LoggerFactory.getLogger(HelloDockerController.class);

    @Autowired
    private InstanceInformationService service;

    @GetMapping("/")
    public String imUpAndRunning(){
        logger.info("Endpoint na raiz foi chamado!");
        return "{healthy:true}";
    }

    @RequestMapping("/hello-docker")
    public HelloDocker helloDocker(){
        logger.info("Endpoint /hello-docker foi chamado!");
        return new HelloDocker(
                "Hello Docker - V1",
                service.retrieveInstanceInfo()
        );
    }

}
