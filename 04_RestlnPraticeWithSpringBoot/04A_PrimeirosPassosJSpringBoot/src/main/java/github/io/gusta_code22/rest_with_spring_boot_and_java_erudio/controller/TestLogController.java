package github.io.gusta_code22.rest_with_spring_boot_and_java_erudio.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TestLogController {

    @GetMapping("/test")
    public String testLog(){
        log.info("Esse é o log de info");
        log.warn("Esse é o log de warn");
        log.debug("Esse é o log de debug");
        log.error("Esse é o log de error");

        return "Logs gerados com sucesso";
    }
}
