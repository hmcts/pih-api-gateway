package uk.gov.hmcts.futurehearings.pip.functional.lists;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import uk.gov.hmcts.futurehearings.pip.functional.common.test.FunctionalTest;
import uk.gov.hmcts.reform.demo.Application;

@Slf4j
@SpringBootTest(classes = {Application.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class ListAPITest extends FunctionalTest {

    @Value("${listRootContext}")
    protected String listRootContext;

    @BeforeAll
    public void initialiseValues() {
        super.initialiseValues();
    }
}
