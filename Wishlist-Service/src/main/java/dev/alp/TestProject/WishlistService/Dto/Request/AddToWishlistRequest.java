package dev.alp.TestProject.WishlistService.Dto.Request;

import lombok.Builder;

@Builder
public record AddToWishlistRequest(
        Integer bookId,
        String imageLink,
        String title,
        String author,
        Double price,
        Integer amount
) {}