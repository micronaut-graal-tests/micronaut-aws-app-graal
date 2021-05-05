package example.micronaut;

import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Header;
import io.micronaut.http.client.annotation.Client;

import java.util.List;

import static io.micronaut.http.HttpHeaders.ACCEPT;
import static io.micronaut.http.HttpHeaders.USER_AGENT;

@Client(GithubApiClient.GITHUB_API_URL)
@Header(name = USER_AGENT, value = "Micronaut HTTP Client")
@Header(name = ACCEPT, value = "application/vnd.github.v3+json, application/json")
public interface GithubApiClient {

    String GITHUB_API_URL = "https://api.github.com";
    String GITHUB_RELEASES_URI = "/repos/micronaut-projects/micronaut-core/releases";

    @Get(GITHUB_RELEASES_URI)
    List<GithubRelease> fetchReleases();
}
