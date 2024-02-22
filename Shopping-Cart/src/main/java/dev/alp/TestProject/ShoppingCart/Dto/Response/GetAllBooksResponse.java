package dev.alp.TestProject.ShoppingCart.Dto.Response;

import lombok.Builder;

@Builder
public record GetAllBooksResponse(
        Integer bookId,
        String imageLink,
        String author,
        String title,
        Double price,
        Integer amount
) {}