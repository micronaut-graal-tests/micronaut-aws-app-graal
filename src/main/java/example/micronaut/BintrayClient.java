package example.micronaut;

import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.annotation.Client;

import java.util.List;

@Client(BintrayClient.BINTRAY_API_URL)
public interface BintrayClient {

    String BINTRAY_API_URL = "https://bintray.com";
    String BINTRAY_URI_PACKAGES = "/api/v1/repos/micronaut/core-releases-local/packages";

    @Get(BINTRAY_URI_PACKAGES)
    List<BintrayPackage> fetchPackages();
}
