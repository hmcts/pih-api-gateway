package uk.gov.hmcts.futurehearings.pip.functional.lists;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import uk.gov.hmcts.futurehearings.pip.functional.common.rest.RestClientTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static uk.gov.hmcts.futurehearings.pip.functional.common.header.factory.HeaderFactory.createHeader;

public class POSTListTest extends ListAPITest {

    @Test
    public void testPOSTList() {
        Response response = RestClientTemplate.performRESTCall(listRootContext, createHeader(),
                                                               authorizationToken,
                                                               "Test",
                                                               null, HttpMethod.POST);
        assertEquals(HttpStatus.CREATED.value(), response.statusCode());
    }
}
