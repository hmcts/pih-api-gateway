package uk.gov.hmcts.futurehearings.pip.functional.common.helper;

import org.springframework.http.MediaType;

import java.util.Collections;
import java.util.Map;

import static uk.gov.hmcts.futurehearings.pip.functional.common.header.dto.factory.PayloadHeaderDTOFactory.buildStandardSystemHeaderPart;
import static uk.gov.hmcts.futurehearings.pip.functional.common.header.dto.factory.PayloadHeaderDTOFactory.convertToMap;

public class CommonHeaderHelper {

    public static Map<String, String> createStandardPayloadHeader() {
        return createHeader(MediaType.APPLICATION_JSON_VALUE);
    }

    private static Map<String, String> createHeader(final String contentType) {
        return Collections.unmodifiableMap(convertToMap(
            buildStandardSystemHeaderPart(contentType,null,null,null,null)));
    }
}
