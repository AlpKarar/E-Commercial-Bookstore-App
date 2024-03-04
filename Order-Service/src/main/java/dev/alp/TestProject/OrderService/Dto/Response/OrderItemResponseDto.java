package dev.alp.TestProject.OrderService.Dto.Response;

import lombok.Builder;

@Builder
public record OrderItemResponseDto(
        String bookId,
        String imageLink,
        String title,
        String author,
        Double price,
        Integer amount
) {}
