package com.magomed.events;

import com.magomed.events.enums.OrderEventTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.NOT_REQUIRED;
import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

public record OrderEvent(
        @Schema(description = "Article the event dedicated to", requiredMode = REQUIRED)
        String article,
        @Schema(description = "Any comment", requiredMode = NOT_REQUIRED)
        String comments,
        @Schema(description = "Type of event", example = "enumeration [CREATED, DELETED]", requiredMode = REQUIRED)
        OrderEventTypeEnum eventType) {}
