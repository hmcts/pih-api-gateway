package uk.gov.hmcts.futurehearings.pip.acceptance.publications;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import uk.gov.hmcts.reform.demo.Application;

@Slf4j
@SpringBootTest(classes = {Application.class})
@ActiveProfiles("acceptance")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GETPublicationsTest extends PublicationValidationTest {

    @Value("${publicationsGetRootContext}")
    private String publicationsGetRootContext;

    @Test
    void testGetPublications() {
    }
}
