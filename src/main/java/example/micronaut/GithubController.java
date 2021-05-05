package example.micronaut;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

import java.util.List;

@Controller("/github")
public class GithubController {

    private final GithubApiClient githubApiClient;

    public GithubController(GithubApiClient githubApiClient) {
        this.githubApiClient = githubApiClient;
    }

    @Get(uri = "/releases", produces = MediaType.APPLICATION_JSON_STREAM)
    List<GithubRelease> fetchReleases() {
        return githubApiClient.fetchReleases();
    }
}
