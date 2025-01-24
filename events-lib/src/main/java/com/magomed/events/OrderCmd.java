package com.magomed.events;

import com.magomed.events.enums.OrderCmdActionEnum;
import io.swagger.v3.oas.annotations.media.Schema;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@Schema(description = "Order command model")
public record OrderCmd(
        @Schema(description = "Command type to be executed", example = "enumeration [CREATE, DELETE]", requiredMode = REQUIRED)
        OrderCmdActionEnum action,
        @Schema(description = "Article to order or cancel", requiredMode = REQUIRED)
        String article,
        @Schema(description = "Amount of articles", requiredMode = REQUIRED)
        int amount
) {}
