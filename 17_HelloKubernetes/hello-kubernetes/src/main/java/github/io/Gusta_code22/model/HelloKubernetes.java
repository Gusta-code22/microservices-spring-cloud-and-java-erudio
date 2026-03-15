package github.io.Gusta_code22.model;

public record HelloKubernetes(String content, String enviroment) {
    public HelloKubernetes(String content, String enviroment) {
        this.content = content;
        this.enviroment = enviroment;
    }
}
