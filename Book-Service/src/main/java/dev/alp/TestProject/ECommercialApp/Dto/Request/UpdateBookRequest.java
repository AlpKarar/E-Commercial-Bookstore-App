package dev.alp.TestProject.ECommercialApp.Dto.Request;

public record UpdateBookRequest(
        Integer id,
        String imageLink,
        String title,
        String author
) {}