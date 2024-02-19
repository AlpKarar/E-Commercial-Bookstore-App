package dev.alp.TestProject.WishlistService.Service;

import com.mongodb.MongoWriteException;
import dev.alp.TestProject.WishlistService.Dto.Request.AddToWishlistRequest;
import dev.alp.TestProject.WishlistService.Dto.Response.AddToWishlistResponse;
import dev.alp.TestProject.WishlistService.Dto.Response.GetFromWishlistResponse;
import dev.alp.TestProject.WishlistService.Model.Wishlist;
import dev.alp.TestProject.WishlistService.Repository.WishlistRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WishlistService {

    private final WishlistRepository wishlistRepository;

    public WishlistService(WishlistRepository wishlistRepository) {
        this.wishlistRepository = wishlistRepository;
    }

    public List<GetFromWishlistResponse> getAllBooksInWishlist() {
        return wishlistRepository.findAll()
                .stream()
                .map(book -> GetFromWishlistResponse.builder()
                        .id(book.getId())
                        .bookId(book.getBookId())
                        .imageLink(book.getImageLink())
                        .title(book.getTitle())
                        .author(book.getAuthor())
                        .build()
                )
                .toList();
    }

    public AddToWishlistResponse addBookToWishlist(AddToWishlistRequest request) throws Exception {
        if (wishlistRepository.existsByBookId(request.bookId())) {
            throw new Exception("Duplicate Key Error - Book ID: " + request.bookId());
        }

        Wishlist bookToAdd = wishlistRepository.save(Wishlist.builder()
                .bookId(request.bookId())
                .imageLink(request.imageLink())
                .title(request.title())
                .author(request.author())
                .build());

        return AddToWishlistResponse.builder()
                .id(bookToAdd.getId())
                .bookId(bookToAdd.getBookId())
                .imageLink(bookToAdd.getImageLink())
                .title(bookToAdd.getTitle())
                .author(bookToAdd.getAuthor())
                .build();
    }

    public void removeBookFromWishlist(Integer id) {
        wishlistRepository.deleteByBookId(id);
    }
}
