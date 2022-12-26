package example.micronaut;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

@Controller("/jokes")
public class JokeController {

    private final ChuckNorrisClient client;

    public JokeController(ChuckNorrisClient client) {
        this.client = client;
    }

    @Get("/nerdy")
    public ChuckNorrisJoke getRndNerdy() {
        return client.getRandomNerdyJoke().orElse(null);
    }

    @Get("/{jokeId}")
    public ChuckNorrisJoke getJokeById(String jokeId) {
        return client.findJokeById(jokeId).orElse(null);
    }

}
