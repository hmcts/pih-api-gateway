package uk.gov.hmcts.futurehearings.pip.acceptance.common.delegate;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static uk.gov.hmcts.futurehearings.pip.acceptance.common.RestClientTemplate.performRESTCall;

@Slf4j
@Component("CommonDelegate")
public class CommonDelegateImpl implements CommonDelegate {

    public Response test_expected_response_for_supplied_header(final String authorizationToken,
                                                               final String targetURL,
                                                               final String inputFile,
                                                               final Map<String, String> standardHeaderMap,
                                                               final Headers headers,
                                                               final Map<String, String> params,
                                                               final HttpMethod httpMethod,
                                                               final HttpStatus status) {

        Headers standardWireMockHeaders;
        if (Objects.nonNull(standardHeaderMap) && standardHeaderMap.size() > 0) {
            standardWireMockHeaders = convertHeaderMapToWireMockHeaders(standardHeaderMap);
        } else {
            standardWireMockHeaders = headers;
        }

        return performRESTCall(standardWireMockHeaders, authorizationToken, null, targetURL, params,
                        status, httpMethod);
    }

    private static final Headers convertHeaderMapToWireMockHeaders(final Map<String, String> headerMap) {
        List<Header> listOfHeaders = new ArrayList<>();
        headerMap.forEach((key, value) -> {
            Header header = new Header(key, value);
            listOfHeaders.add(header);
        });
        Headers headers = new Headers(listOfHeaders);
        return headers;
    }
}
