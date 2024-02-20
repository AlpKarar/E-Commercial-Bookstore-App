package dev.alp.TestProject.ShoppingCart.Dto.Request;

import lombok.Builder;

@Builder
public record AddedBookRequest(
        Integer bookId,
        String imageLink,
        String title,
        String author,
        Double price,
        Integer amount
){}
