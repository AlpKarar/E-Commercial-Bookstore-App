package dev.alp.TestProject.ECommercialApp.Dto.Response;

import lombok.Builder;

@Builder
public record CreateBookResponse(
        Integer id,
        String imageLink,
        String title,
        String author
) {}