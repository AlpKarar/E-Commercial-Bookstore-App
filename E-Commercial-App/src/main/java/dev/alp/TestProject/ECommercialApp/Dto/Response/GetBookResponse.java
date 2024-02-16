package dev.alp.TestProject.ECommercialApp.Dto.Response;

import lombok.Builder;

@Builder
public record GetBookResponse(
        Integer id,
        String imageLink,
        String title,
        String author
) {}
