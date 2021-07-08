package uk.gov.hmcts.futurehearings.pip.functional.common;

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import java.util.Map;
import java.util.Objects;

@Slf4j
public class RestClientTemplate {
    public static Response performRESTCall(final Headers headers,
                                         final String authorizationToken,
                                         final String requestBodyPayload,
                                         final String requestURL,
                                         final Map<String, String> params,
                                         final HttpStatus expectedHttpStatus,
                                         final HttpMethod httpMethod) {

        switch (httpMethod) {
            case POST:
                return RestAssured
                    .expect().that().statusCode(expectedHttpStatus.value())
                    .given()
                    .headers(headers)
                    .auth()
                    .oauth2(authorizationToken)
                    .basePath(requestURL)
                    .body(requestBodyPayload)
                    .when()
                    .post().then().extract().response();
            case PUT:
                return RestAssured
                    .expect().that().statusCode(expectedHttpStatus.value())
                    .given()
                    .headers(headers)
                    .auth()
                    .oauth2(authorizationToken)
                    .basePath(requestURL)
                    .body(requestBodyPayload)
                    .when()
                    .put().then().extract().response();
            case DELETE:
                return RestAssured
                    .expect().that().statusCode(expectedHttpStatus.value())
                    .given()
                    .headers(headers)
                    .auth()
                    .oauth2(authorizationToken)
                    .basePath(requestURL)
                    .body(requestBodyPayload)
                    .when()
                    .delete().then().extract().response();
            case GET:
                if (Objects.isNull(params) || params.size() == 0) {
                    return RestAssured
                        .given()
                        .headers(headers)
                        .auth()
                        .oauth2(authorizationToken)
                        .basePath(requestURL)
                        .when()
                        .get().then().extract().response();
                } else {
                    Response response;
                    response = RestAssured
                        .given()
                        .queryParams(params)
                        .headers(headers)
                        .auth()
                        .oauth2(authorizationToken)
                        .basePath(requestURL)
                        .when()
                        .get().then().extract().response();
                    return response;
                }
            case OPTIONS:
                return RestAssured.expect().that().statusCode(expectedHttpStatus.value())
                    .given()
                    .headers(headers)
                    .auth()
                    .oauth2(authorizationToken)
                    .basePath(requestURL)
                    .when()
                    .options().then().extract().response();
            default:
                throw new IllegalArgumentException("HTTP method not identified");
        }
    }
}
