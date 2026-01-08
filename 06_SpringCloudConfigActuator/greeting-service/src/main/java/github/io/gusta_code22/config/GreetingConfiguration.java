package github.io.gusta_code22.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@ConfigurationProperties("greeting-service")
@RefreshScope
public class GreetingConfiguration {

    public GreetingConfiguration() {

    }

    private String greeting;
    private String defaultValue;

    public String defaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String greeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }
}
