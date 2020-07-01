package micronaut.example;

import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.annotation.Client;

import java.util.Optional;

@Client("https://api.icndb.com")
public interface IcndbClient {

    @Get("/jokes/random/?limitTo=[nerdy]")
    Optional<Joke> getRandomNerdyJoke();
}
