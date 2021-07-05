package uk.gov.hmcts.futurehearings.pip.functional.common.rest;

import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public class RestClientTemplate {
    public static Response callRestEndpointWithPayload(final String apiURL,
                                                       final Map<String, Object> headersAsMap,
                                                       final String authorizationToken,
                                                       final String payloadBody,
                                                       final HttpMethod httpMethod,
                                                       final HttpStatus httpStatus) {

        Response response;
        switch (httpMethod) {
            case POST:
                response = RestAssured.given().body(payloadBody)
                    .headers(headersAsMap)
                    .auth().oauth2(authorizationToken)
                    .basePath(apiURL)
                    .when().post().then().extract().response();
                break;
            case PUT:
                response = RestAssured.given().body(payloadBody)
                    .headers(headersAsMap)
                    .auth().oauth2(authorizationToken)
                    .basePath(apiURL)
                    .when().put().then().extract().response();
                break;
            case DELETE:
                response = RestAssured.given().body(payloadBody)
                    .headers(headersAsMap)
                    .auth().oauth2(authorizationToken)
                    .basePath(apiURL)
                    .when().delete().then().extract().response();
                break;
            case GET :
                response = RestAssured.given()
                        .headers(headersAsMap)
                        .auth().oauth2(authorizationToken)
                        .basePath(apiURL)
                        .when().get().then().extract().response();
                break;
            default:
                throw new UnsupportedOperationException("This REST method is not Supported....");
        }
        return response;

    }
}
