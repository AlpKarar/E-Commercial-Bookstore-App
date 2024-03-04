package dev.alp.TestProject.OrderService.Dto.Response;

import lombok.Builder;

import java.util.List;

@Builder
public record CreateOrderResponse(
        String accountId,
        Double total,
        List<OrderItemResponseDto> orderItems
) {}
