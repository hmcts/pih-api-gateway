package uk.gov.hmcts.futurehearings.pip.acceptance.publications;

import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import uk.gov.hmcts.reform.demo.Application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static uk.gov.hmcts.futurehearings.pip.acceptance.common.helper.CommonHeaderHelper.createStandardPayloadHeader;

@Slf4j
@SpringBootTest(classes = {Application.class})
@ActiveProfiles("acceptance")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GETPublicationsTest extends PublicationValidationTest {

    @Value("${publicationsGetRootContext}")
    private String publicationsGetRootContext;

    @Test
    void testGetPublications() {
        Response response = commonDelegate.test_expected_response_for_supplied_header(getAuthorizationToken(),
                                                                                      publicationsGetRootContext, null,
                                                                                      createStandardPayloadHeader(),
                                                                                      null, null,
                                                                                      HttpMethod.GET, HttpStatus.OK);
        assertEquals(HttpStatus.OK.value(), response.statusCode());
    }
}
