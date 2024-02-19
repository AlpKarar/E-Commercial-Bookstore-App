package dev.alp.TestProject.WishlistService.Dto.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
public record AddToWishlistResponse(
        String id,
        Integer bookId,
        String imageLink,
        String title,
        String author
) {}