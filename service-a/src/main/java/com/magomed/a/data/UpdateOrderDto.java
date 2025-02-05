package com.magomed.a.data;

import java.util.List;

public record UpdateOrderDto(int orderId, List<String> articleNames) {
}
