package com.magomed.a.data;

import java.util.List;

public record OrderDto(int amount, int orderId, List<ArticleDto> articles) {
}
