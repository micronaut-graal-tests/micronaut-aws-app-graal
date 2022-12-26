package example.micronaut

import com.amazonaws.serverless.proxy.internal.testutils.AwsProxyRequestBuilder
import com.amazonaws.serverless.proxy.internal.testutils.MockLambdaContext
import com.amazonaws.serverless.proxy.model.AwsProxyRequest
import com.amazonaws.serverless.proxy.model.AwsProxyResponse
import com.amazonaws.services.lambda.runtime.Context
import com.fasterxml.jackson.databind.ObjectMapper
import io.micronaut.function.aws.proxy.MicronautLambdaHandler
import io.micronaut.http.HttpHeaders
import io.micronaut.http.HttpMethod
import io.micronaut.http.HttpStatus
import io.micronaut.http.MediaType
import io.micronaut.http.uri.UriBuilder
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification

class JokeControllerSpec extends Specification {

    @Shared
    @AutoCleanup
    MicronautLambdaHandler handler = new MicronautLambdaHandler()

    @Shared
    Context lambdaContext = new MockLambdaContext()

    @Shared
    ObjectMapper objectMapper = handler.applicationContext.getBean(ObjectMapper)

    void 'get random nerdy joke'() {
        given:
        AwsProxyRequest request = new AwsProxyRequestBuilder('/jokes/nerdy', HttpMethod.GET.toString())
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
            .build()

        when:
        AwsProxyResponse response = handler.handleRequest(request, lambdaContext)

        then:
        HttpStatus.OK.code == response.statusCode
        response.body

        when:
        Map jokeResponse = objectMapper.readValue(response.body, Map<String, Object>)

        then:
        jokeResponse
        jokeResponse.updated_at
        jokeResponse.created_at
        jokeResponse.value
        jokeResponse.id
    }

    void 'get a joke by id'() {
        given:
        String chuckNorrisId = "s2jM0EbWTFe-yMDkvM_JfQ"
        String id =  chuckNorrisId
        AwsProxyRequest request = new AwsProxyRequestBuilder(UriBuilder.of('/jokes').path(id).build().toString(), HttpMethod.GET.toString())
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
            .build()

        when:
        AwsProxyResponse response = handler.handleRequest(request, lambdaContext)

        then:
        HttpStatus.OK.code == response.statusCode
        response.body

        when:
        Map jokeResponse = objectMapper.readValue(response.body, Map<String, Object>)

        then:
        jokeResponse
        jokeResponse.updated_at
        jokeResponse.created_at
        "one day 7 year old Chuck Norris swam to europe, next day nazi surrendered" == jokeResponse.value
        jokeResponse.id
    }

}
