package micronaut.example;

import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.annotation.Client;

import javax.validation.constraints.NotBlank;
import java.util.Optional;

@Client("https://api.icndb.com")
public interface IcndbClient {

    @Get("/jokes/random/?limitTo=[nerdy]")
    Optional<Joke> getRandomNerdyJoke();

    @Get("/jokes/{jokeId}")
    Optional<Joke> findJokeById(@NotBlank String jokeId);
}
