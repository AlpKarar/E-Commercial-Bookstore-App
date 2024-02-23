package dev.alp.TestProject.WishlistService.UnitTest;

import dev.alp.TestProject.WishlistService.Dto.Request.AddToWishlistRequest;
import dev.alp.TestProject.WishlistService.Dto.Response.AddToWishlistResponse;
import dev.alp.TestProject.WishlistService.Dto.Response.GetFromWishlistResponse;
import dev.alp.TestProject.WishlistService.Model.Wishlist;
import dev.alp.TestProject.WishlistService.Repository.WishlistRepository;
import dev.alp.TestProject.WishlistService.Service.WishlistService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class WishlistServiceUnitTest {

    @Mock
    public WishlistRepository wishlistRepository;

    @InjectMocks
    public WishlistService wishlistService;

    List<Wishlist> booksInWishlist = new ArrayList<>();

    @BeforeEach
    public void setup() {
        Wishlist book1 = Wishlist.builder()
                .id("65d25ef60344d82fd3ee10a2")
                .bookId(1)
                .imageLink("http://abc.com/img-1")
                .title("title-1")
                .author("author-1")
                .price(11.99)
                .build();

        Wishlist book2 = Wishlist.builder()
                .id("65d267ad38f9bd6e239a70d5")
                .bookId(2)
                .imageLink("http://abc.com/img-2")
                .title("title-2")
                .author("author-2")
                .price(17.50)
                .build();

        Wishlist book3 = Wishlist.builder()
                .id("65d25ef60344d82fd3gjwsu")
                .bookId(3)
                .imageLink("http://abc.com/img-3")
                .title("title-3")
                .author("author-3")
                .price(23.00)
                .build();

        booksInWishlist.addAll(List.of(book1, book2, book3));
    }

    @Test
    public void whenGetAllBooksInWishlist_thenReturnBooksInWishlist() {
        List<GetFromWishlistResponse> expectedResponse = booksInWishlist
                .stream()
                .map(book -> GetFromWishlistResponse.builder()
                        .id(book.getId())
                        .bookId(book.getBookId())
                        .imageLink(book.getImageLink())
                        .title(book.getTitle())
                        .author(book.getAuthor())
                        .price(book.getPrice())
                        .build()
                ).toList();

        Mockito.when(wishlistRepository.findAll()).thenReturn(booksInWishlist);

        List<GetFromWishlistResponse> actualResponse = wishlistService.getAllBooksInWishlist();

        Assertions.assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void whenAddBookToWishlist_thenReturnAddedBookInWishlist() throws Exception {
        AddToWishlistRequest request;
        Wishlist bookToAdd;
        Wishlist addedBook;
        AddToWishlistResponse expectedResponse;

        for (Wishlist book : booksInWishlist) {
            request = AddToWishlistRequest.builder()
                    .bookId(book.getBookId())
                    .imageLink(book.getImageLink())
                    .title(book.getTitle())
                    .author(book.getAuthor())
                    .price(book.getPrice())
                    .build();

            bookToAdd = Wishlist.builder()
                    .bookId(request.bookId())
                    .imageLink(request.imageLink())
                    .title(request.title())
                    .author(request.author())
                    .price(request.price())
                    .build();

            addedBook = Wishlist.builder()
                    .id(book.getId())
                    .bookId(request.bookId())
                    .imageLink(request.imageLink())
                    .title(request.title())
                    .author(request.author())
                    .price(request.price())
                    .build();

            expectedResponse = AddToWishlistResponse.builder()
                    .id(addedBook.getId())
                    .bookId(addedBook.getBookId())
                    .imageLink(addedBook.getImageLink())
                    .title(addedBook.getTitle())
                    .author(addedBook.getAuthor())
                    .price(addedBook.getPrice())
                    .build();

            Mockito.when(wishlistRepository.existsByBookId(request.bookId())).thenReturn(false);
            Mockito.when(wishlistRepository.save(bookToAdd)).thenReturn(addedBook);

            AddToWishlistResponse actualResponse = wishlistService.addBookToWishlist(request);

            Assertions.assertEquals(expectedResponse, actualResponse);
        }
    }

    @Test
    public void whenAddBookToWishlistAndBookIsDuplicated_thenThrowExceptionWithCustomMessage() {
        int bookId;

        for (Wishlist book : booksInWishlist) {
            AddToWishlistRequest request = AddToWishlistRequest.builder()
                    .bookId(book.getBookId())
                    .imageLink(book.getImageLink())
                    .title(book.getTitle())
                    .author(book.getAuthor())
                    .price(book.getPrice())
                    .build();

            bookId = request.bookId();

            Mockito.when(wishlistRepository.existsByBookId(bookId))
                    .thenReturn(true);
            String expectedResponse = "Duplicate Key Error - Book ID: " + bookId;

            Exception exception = Assertions.assertThrows(Exception.class, () -> {
                wishlistService.addBookToWishlist(request);
            });

            Assertions.assertEquals(expectedResponse, exception.getMessage());
        }
    }
}
