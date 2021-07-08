package uk.gov.hmcts.futurehearings.pip.functional.lists;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import uk.gov.hmcts.futurehearings.pip.functional.common.rest.RestClientTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static uk.gov.hmcts.futurehearings.pip.functional.common.header.factory.HeaderFactory.createHeader;

public class PUTListTest extends ListAPITest {
    @Test
    public void testPUTList() {
        Response response = RestClientTemplate.performRESTCall(listRootContext, createHeader(),
                                                               authorizationToken,
                                                               "Test",
                                                               null, HttpMethod.PUT);
        assertEquals(HttpStatus.NO_CONTENT.value(), response.statusCode());
    }
}
