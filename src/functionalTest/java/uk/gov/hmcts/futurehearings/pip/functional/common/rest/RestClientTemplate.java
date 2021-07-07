package uk.gov.hmcts.futurehearings.pip.functional.common.rest;

import java.util.Map;
import java.util.Objects;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public class RestClientTemplate {
    public static Response performRESTCall(final String apiURL,
                                           final Map<String, Object> headersAsMap,
                                           final String authorizationToken,
                                           final String payloadBody,
                                           final Map<String, String> params,
                                           final HttpMethod httpMethod) {

        switch (httpMethod) {
            case POST:
                return RestAssured.given().body(payloadBody)
                    .headers(headersAsMap)
                    .auth()
                    .oauth2(authorizationToken)
                    .basePath(apiURL)
                    .when()
                    .post().then().extract().response();
            case PUT:
                return RestAssured.given().body(payloadBody)
                    .headers(headersAsMap)
                    .auth()
                    .oauth2(authorizationToken)
                    .basePath(apiURL)
                    .when()
                    .put().then().extract().response();
            case DELETE:
                 return RestAssured.given().body(payloadBody)
                     .headers(headersAsMap)
                     .auth()
                     .oauth2(authorizationToken)
                     .basePath(apiURL)
                     .when()
                     .delete().then().extract().response();
            case GET :
                if (Objects.isNull(params) || params.size() == 0) {
                    return RestAssured.given()
                        .headers(headersAsMap)
                        .auth()
                        .oauth2(authorizationToken)
                        .basePath(apiURL)
                        .when()
                        .get().then().extract().response();
                } else {
                    return RestAssured.given()
                        .headers(headersAsMap)
                        .queryParams(params)
                        .auth()
                        .oauth2(authorizationToken)
                        .basePath(apiURL)
                        .when()
                        .get().then().extract().response();
                }
            default:
                throw new UnsupportedOperationException("This REST method is not Supported....");
        }
    }
}
