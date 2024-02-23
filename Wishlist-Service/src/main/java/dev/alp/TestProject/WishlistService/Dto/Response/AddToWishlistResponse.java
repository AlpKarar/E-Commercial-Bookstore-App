package dev.alp.TestProject.WishlistService.Dto.Response;

import lombok.Builder;

@Builder
public record AddToWishlistResponse(
        String id,
        Integer bookId,
        String imageLink,
        String title,
        String author,
        Double price,
        Integer amount
) {}