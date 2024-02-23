package dev.alp.TestProject.WishlistService.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("book_in_wishlist")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Wishlist {

    @Id
    private String id;

    @Indexed(unique = true)
    private Integer bookId;
    private String imageLink;
    private String title;
    private String author;
    private Double price;
    private Integer amount;
}
