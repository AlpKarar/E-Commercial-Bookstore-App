package dev.alp.TestProject.OrderService.Dto.Request;

import lombok.Builder;

@Builder
public record OrderItemRequestDto(
        String bookId,
        String imageLink,
        String title,
        String author,
        Double price,
        Integer amount
) {}
