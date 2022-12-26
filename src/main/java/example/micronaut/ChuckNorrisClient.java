package example.micronaut;

import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.annotation.Client;

import javax.validation.constraints.NotBlank;
import java.util.Optional;

@Client("https://api.chucknorris.io")
public interface ChuckNorrisClient {

    @Get("/jokes/random")
    Optional<ChuckNorrisJoke> getRandomNerdyJoke();

    @Get("/jokes/{jokeId}")
    Optional<ChuckNorrisJoke> findJokeById(@NotBlank String jokeId);
}
