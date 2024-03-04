package dev.alp.TestProject.ECommercialApp.Dto.Request;

import lombok.Builder;

@Builder
public record CreateBookRequest(
        String imageLink,
        String title,
        String author,
        Double price,
        Integer stock
) {}