package uk.gov.hmcts.futurehearings.pip.functional.publications;

import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import uk.gov.hmcts.futurehearings.pip.functional.common.RestClientTemplate;
import uk.gov.hmcts.reform.demo.Application;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@SpringBootTest(classes = {Application.class})
@ActiveProfiles("functional")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class POSTPublicationsTest extends PublicationValidationTest {

    @Test
    void testPostPublication() {
        Response response = RestClientTemplate.performRESTCall(null,
                                                               getAuthorizationToken(),
                                                               null,
                                                               "URL",
                                                               null,
                                                               HttpStatus.OK,
                                                               HttpMethod.POST);

        assertEquals(HttpStatus.CREATED.value(), response.statusCode());
    }

    @Test
    void testPostPublicationNotAuthorised() {
        Response response = RestClientTemplate.performRESTCall(null,
                                                               getAuthorizationToken(),
                                                               null,
                                                               "URL",
                                                               null,
                                                               HttpStatus.UNAUTHORIZED,
                                                               HttpMethod.POST);

        assertEquals(HttpStatus.UNAUTHORIZED.value(), response.statusCode());
    }
}
