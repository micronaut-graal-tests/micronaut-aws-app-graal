package micronaut.example;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

@Controller("/jokes")
public class JokeController {

    private final IcndbClient client;

    public JokeController(IcndbClient client) {
        this.client = client;
    }

    @Get("/nerdy")
    public Joke getRndNerdy() {
        return client.getRandomNerdyJoke().orElse(null);
    }

}
