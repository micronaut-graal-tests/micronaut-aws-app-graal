package micronaut.example;

import io.micronaut.core.annotation.Introspected;

@Introspected
public class JokeRequest {

    private String category;

    public JokeRequest() {
    }

    public JokeRequest(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
