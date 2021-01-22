package example.micronaut;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

import java.util.List;

@Controller("/bintray")
public class BintrayController {

    private final BintrayClient bintrayClient;

    public BintrayController(BintrayClient bintrayClient) {
        this.bintrayClient = bintrayClient;
    }

    @Get("/packages")
    List<BintrayPackage> packages() {
        return bintrayClient.fetchPackages();
    }
}
