package uk.gov.hmcts.futurehearings.pip.smoke.common.rest;

import io.restassured.response.Response;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class RestClientTemplate {

    public static Response makeGetRequest(final Map<String, String> headers,
                                          final String authorizationToken,
                                          final String rootContext) {
        return given()
            .headers(headers)
            .auth().oauth2(authorizationToken)
            .basePath(rootContext)
            .when().get();
    }
}
