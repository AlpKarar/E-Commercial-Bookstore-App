package dev.alp.TestProject.OrderService.Dto.Request;

import lombok.Builder;

import java.util.List;

@Builder
public record CreateOrderRequest(
        String accountId,
        Double total,
        List<OrderItemRequestDto> orderItems
) {}
