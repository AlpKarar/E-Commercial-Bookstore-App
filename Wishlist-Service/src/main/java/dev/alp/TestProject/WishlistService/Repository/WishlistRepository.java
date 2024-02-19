package dev.alp.TestProject.WishlistService.Repository;

import dev.alp.TestProject.WishlistService.Model.Wishlist;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WishlistRepository extends MongoRepository<Wishlist, String> {

    boolean existsByBookId(Integer bookId);
    void deleteByBookId(Integer bookId);
}
