package dev.alp.TestProject.ECommercialApp.Dto.Response;

import lombok.Builder;

@Builder
public record UpdateBookResponse(
        Integer id,
        String imageLink,
        String title,
        String author,
        Double price
) {}