package com.magomed.events;

import com.magomed.events.enums.ArticleCmdActionEnum;
import io.swagger.v3.oas.annotations.media.Schema;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@Schema(description = "Article command model")
public record ArticleCmd(
        @Schema(description = "Command type to be executed", example = "CREATE", requiredMode = REQUIRED)
        ArticleCmdActionEnum action,
        @Schema(description = "Article name", requiredMode = REQUIRED)
        String article,
        @Schema(description = "Article's weight", requiredMode = REQUIRED)
        int weight
) {
}
