package github.io.Gusta_code22.controller;

import github.io.Gusta_code22.enviroment.InstanceInformationService;
import github.io.Gusta_code22.model.HelloKubernetes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class HelloKubernetesController {

    @Autowired
    private InstanceInformationService informationService;

    @GetMapping(path = "/")
    public String isUpping() {
        return "{healthy:true}";
    }

    @GetMapping("/hello-kubernetes")
    public HelloKubernetes helloKubernetes(){
        return new HelloKubernetes(
                "Hello Kubernetes - V2",
                informationService.retrieveInstanceInfo()
        );
    }


}
