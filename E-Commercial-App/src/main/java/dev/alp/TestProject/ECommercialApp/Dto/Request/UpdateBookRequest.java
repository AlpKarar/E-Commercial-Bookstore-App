package dev.alp.TestProject.ECommercialApp.Dto.Request;

import lombok.Builder;

@Builder
public record UpdateBookRequest(
        Integer id,
        String imageLink,
        String title,
        String author
) {}