package uk.gov.hmcts.futurehearings.pip.functional.common.header.dto;

import lombok.*;
import lombok.experimental.Accessors;

@Setter(AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@Builder
@Accessors(fluent = true)
@ToString
@EqualsAndHashCode
public class SystemHeaderDTO {
    private String accept;
    private String contentType;
    private String contentEncoding;
    private String authorization;
    private String cacheControl;
}
