package uk.gov.hmcts.futurehearings.pip.acceptance.common.test;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

@Slf4j
@Setter
@Getter
public abstract class PIPCommonTest {
    private String authorizationToken;

    @BeforeAll
    static void beforeAll() {
    }

    @BeforeEach
    public void beforeEach(TestInfo info) {
    }

    @AfterEach
    public void afterEach(TestInfo info) {
    }

    @AfterAll
    static void afterAll() {
    }
}
