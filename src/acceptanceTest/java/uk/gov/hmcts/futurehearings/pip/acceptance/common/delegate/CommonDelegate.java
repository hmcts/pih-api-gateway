package uk.gov.hmcts.futurehearings.pip.acceptance.common.delegate;

import java.util.Map;

import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

public interface CommonDelegate {
    public Response test_expected_response_for_supplied_header(final String authorizationToken,
                                                               final String targetURL,
                                                               final String inputFile,
                                                               final Map<String, String> standardHeaderMap,
                                                               final Headers headers,
                                                               final Map<String, String> params,
                                                               final HttpMethod httpMethod,
                                                               final HttpStatus status);

}
