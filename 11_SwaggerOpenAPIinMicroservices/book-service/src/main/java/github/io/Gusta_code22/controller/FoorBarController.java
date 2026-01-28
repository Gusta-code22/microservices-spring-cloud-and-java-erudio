package github.io.Gusta_code22.controller;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Tag(name = "Foo bar Endpoint")
@RestController
@RequestMapping("/book-service")
public class FoorBarController {
    private Logger logger = LoggerFactory.getLogger(FoorBarController.class);

//    @Retry(name = "default")

    @GetMapping("/foo-bar")
//    @Retry(name = "foo-bar")
//    @Retry(name = "foo-bar", fallbackMethod = "fallbackMethod")
//    @CircuitBreaker(name = "default", fallbackMethod = "fallbackMethod")
//    @RateLimiter(name = "default")
    @Bulkhead(name = "default")
    public String fooBar() {

        logger.info("Request to foo-bar is received!!");
        /*var reponse = new RestTemplate()
                .getForEntity("http://localhost:8080/foo-bar", String.class);*/
        return "Foo Bar!!";

//        return reponse.getBody();
    }

    public String fallbackMethod(Exception ex){
        return "fallbackMethod foo-bar";

    }
}
