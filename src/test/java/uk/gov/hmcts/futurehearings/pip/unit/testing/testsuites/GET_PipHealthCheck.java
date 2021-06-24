package uk.gov.hmcts.futurehearings.pip.unit.testing.testsuites;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import uk.gov.hmcts.futurehearings.pip.unit.testing.utils.TestReporter;
import uk.gov.hmcts.reform.demo.Application;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static uk.gov.hmcts.futurehearings.pip.unit.testing.utils.HeatlhCheckResponseVerifier.thenValidateResponseForHealthCheck;

@Slf4j
@SpringBootTest(classes = {Application.class})
@ActiveProfiles("test")
@ExtendWith(TestReporter.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("GET /health - PIP Health Check")
public class GET_PipHealthCheck {

    @Value("${resourcesApiRootContext}")
    private String resourcesApiRootContext;
    @Value("${targetInstance}")
    private String targetInstance;
    @Value("${targetSubscriptionKey}")
    private String targetSubscriptionKey;

    private final Map<String, Object> headersAsMap = new HashMap<>();

    @BeforeEach
    void initialiseValues() {
        headersAsMap.put("Content-Type", "application/json");
        headersAsMap.put("Ocp-Apim-Subscription-Key", targetSubscriptionKey);
    }

    @Test
    @DisplayName("Test for PIP Health Check")
    void testInvokeHealthCheckForPip() {
        final Response response = whenHeatlhCheckEndPointIsInvoked();
        thenValidateResponseForHealthCheck(response);
    }

    private Response whenHeatlhCheckEndPointIsInvoked() {
        return retrieveResourcesResponseForHealthCheck(targetInstance, resourcesApiRootContext, headersAsMap);
    }

    private Response retrieveResourcesResponseForHealthCheck(final String basePath, final String api, final Map<String, Object> headersAsMap) {

        return given()
            //.auth()
            //.oauth2(accessToken)
            .headers(headersAsMap)
            .baseUri(basePath)
            .basePath(api)
            .when().get().then().extract().response();
    }


}

