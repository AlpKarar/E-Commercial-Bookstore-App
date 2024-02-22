package dev.alp.TestProject.ShoppingCart.UnitTest;

import dev.alp.TestProject.ShoppingCart.Dto.Request.AddedBookRequest;
import dev.alp.TestProject.ShoppingCart.Dto.Response.AddedBookResponse;
import dev.alp.TestProject.ShoppingCart.Dto.Response.GetAllBooksResponse;
import dev.alp.TestProject.ShoppingCart.Dto.Response.UpdateBookResponse;
import dev.alp.TestProject.ShoppingCart.Model.ShoppingCart;
import dev.alp.TestProject.ShoppingCart.Repository.ShoppingCartRepository;
import dev.alp.TestProject.ShoppingCart.Service.ShoppingCartService;
import jakarta.persistence.EntityNotFoundException;
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
import java.util.stream.IntStream;

@ExtendWith(MockitoExtension.class)
public class ShoppingCartServiceUnitTest {

    @Mock
    ShoppingCartRepository shoppingCartRepository;

    @InjectMocks
    ShoppingCartService shoppingCartService;

    List<ShoppingCart> booksInCart = new ArrayList<>();

    @BeforeEach
    public void setup() {
        ShoppingCart shoppingCart1 = ShoppingCart.builder()
                .id(1L)
                .bookId(1)
                .imageLink("http://abc.co/img-1")
                .title("title-1")
                .author("author-1")
                .price(65.0)
                .amount(2)
                .build();

        ShoppingCart shoppingCart2 = ShoppingCart.builder()
                .id(2L)
                .bookId(2)
                .imageLink("http://abc.co/img-2")
                .title("title-2")
                .author("author-2")
                .price(105.0)
                .amount(3)
                .build();

        ShoppingCart shoppingCart3 = ShoppingCart.builder()
                .id(3L)
                .bookId(3)
                .imageLink("http://abc.co/img-3")
                .title("title-3")
                .author("author-3")
                .price(75.0)
                .amount(1)
                .build();

        booksInCart.addAll(List.of(shoppingCart1, shoppingCart2, shoppingCart3));
    }

    @Test
    public void whenGetAllBooks_thenReturnAllBooksInCart() {
        List<GetAllBooksResponse> expectedResponse = new ArrayList<>();

        for (ShoppingCart bookInCart : booksInCart) {
            expectedResponse.add(GetAllBooksResponse.builder()
                    .bookId(bookInCart.getBookId())
                    .imageLink(bookInCart.getImageLink())
                    .title(bookInCart.getTitle())
                    .author(bookInCart.getAuthor())
                    .price(bookInCart.getPrice())
                    .amount(bookInCart.getAmount())
                    .build()
            );
        }

        Mockito.when(shoppingCartRepository.findAll()).thenReturn(booksInCart);
        List<GetAllBooksResponse> actualResponse = shoppingCartService.getAllBooks();

        Assertions.assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void whenAddBookToCart_thenReturnAddedBookResponse() {
        AddedBookRequest request;
        ShoppingCart curBookInCart;
        ShoppingCart savedCurBookInCart;
        AddedBookResponse expectedResponse;
        AddedBookResponse actualResponse;

        for (ShoppingCart bookInCart : booksInCart) {
            request = AddedBookRequest.builder()
                    .bookId(bookInCart.getBookId())
                    .imageLink(bookInCart.getImageLink())
                    .title(bookInCart.getTitle())
                    .author(bookInCart.getAuthor())
                    .price(bookInCart.getPrice())
                    .amount(bookInCart.getAmount())
                    .build();

            curBookInCart = ShoppingCart.builder()
                    .bookId(request.bookId())
                    .imageLink(request.imageLink())
                    .title(request.title())
                    .author(request.author())
                    .price(request.price())
                    .amount(request.amount())
                    .build();

            savedCurBookInCart = ShoppingCart.builder()
                    .id(bookInCart.getId())
                    .bookId(request.bookId())
                    .imageLink(request.imageLink())
                    .title(request.title())
                    .author(request.author())
                    .price(request.price())
                    .amount(request.amount())
                    .build();

            expectedResponse = AddedBookResponse.builder()
                    .bookId(savedCurBookInCart.getBookId())
                    .imageLink(savedCurBookInCart.getImageLink())
                    .title(savedCurBookInCart.getTitle())
                    .author(savedCurBookInCart.getAuthor())
                    .price(savedCurBookInCart.getPrice())
                    .amount(savedCurBookInCart.getAmount())
                    .build();

            Mockito.when(shoppingCartRepository.existsByBookId(request.bookId())).thenReturn(false);
            Mockito.when(shoppingCartRepository.save(curBookInCart)).thenReturn(savedCurBookInCart);

            actualResponse = shoppingCartService.addBookToCart(request);

            Assertions.assertEquals(expectedResponse, actualResponse);
        }
    }

    @Test
    public void whenAddBookToCartAndBookToAddDoesExistInCart_thenIncrementAmountByOneAndReturnAddedBookResponse() {
        AddedBookRequest request;
        ShoppingCart curBookInCart;
        ShoppingCart savedCurBookInCart;
        AddedBookResponse expectedResponse;
        AddedBookResponse actualResponse;

        for (ShoppingCart bookInCart : booksInCart) {
            request = AddedBookRequest.builder()
                    .bookId(bookInCart.getBookId())
                    .imageLink(bookInCart.getImageLink())
                    .title(bookInCart.getTitle())
                    .author(bookInCart.getAuthor())
                    .price(bookInCart.getPrice())
                    .amount(bookInCart.getAmount())
                    .build();

            curBookInCart = ShoppingCart.builder()
                    .bookId(request.bookId())
                    .imageLink(request.imageLink())
                    .title(request.title())
                    .author(request.author())
                    .price(request.price())
                    .amount(request.amount())
                    .build();

            savedCurBookInCart = ShoppingCart.builder()
                    .id(bookInCart.getId())
                    .bookId(request.bookId())
                    .imageLink(request.imageLink())
                    .title(request.title())
                    .author(request.author())
                    .price(request.price())
                    .amount(request.amount() + request.amount())
                    .build();

            expectedResponse = AddedBookResponse.builder()
                    .bookId(savedCurBookInCart.getBookId())
                    .imageLink(savedCurBookInCart.getImageLink())
                    .title(savedCurBookInCart.getTitle())
                    .author(savedCurBookInCart.getAuthor())
                    .price(savedCurBookInCart.getPrice())
                    .amount(savedCurBookInCart.getAmount())
                    .build();

            Mockito.when(shoppingCartRepository.existsByBookId(request.bookId())).thenReturn(true);
            Mockito.when(shoppingCartRepository.findByBookId(request.bookId())).thenReturn(curBookInCart);
            Mockito.when(shoppingCartRepository.save(curBookInCart)).thenReturn(savedCurBookInCart);

            actualResponse = shoppingCartService.addBookToCart(request);

            Assertions.assertEquals(expectedResponse, actualResponse);
        }
    }

    @Test
    public void whenUpdateBookInCart_thenReturnUpdateBookResponse() {
        ShoppingCart bookInCart;
        ShoppingCart bookToUpdate;
        ShoppingCart updatedBook;
        ShoppingCart savedBook;
        UpdateBookResponse expectedResponse;
        UpdateBookResponse actualResponse;

        int[] amounts = IntStream.rangeClosed(1, booksInCart.size()).toArray();
        int newAmount;

        for (int i = 0; i < booksInCart.size(); i++) {
            bookInCart = booksInCart.get(i);
            newAmount = amounts[i];

            bookToUpdate = ShoppingCart.builder()
                    .id(bookInCart.getId())
                    .bookId(bookInCart.getBookId())
                    .imageLink(bookInCart.getImageLink())
                    .title(bookInCart.getTitle())
                    .author(bookInCart.getAuthor())
                    .price(bookInCart.getPrice())
                    .amount(bookInCart.getAmount())
                    .build();

            updatedBook = ShoppingCart.builder()
                    .id(bookToUpdate.getId())
                    .bookId(bookToUpdate.getBookId())
                    .imageLink(bookToUpdate.getImageLink())
                    .title(bookToUpdate.getTitle())
                    .author(bookToUpdate.getAuthor())
                    .price(bookToUpdate.getPrice())
                    .amount(newAmount)
                    .build();

            savedBook = ShoppingCart.builder()
                    .id(updatedBook.getId())
                    .bookId(updatedBook.getBookId())
                    .imageLink(updatedBook.getImageLink())
                    .title(updatedBook.getTitle())
                    .author(updatedBook.getAuthor())
                    .price(updatedBook.getPrice())
                    .amount(updatedBook.getAmount())
                    .build();

            expectedResponse = UpdateBookResponse.builder()
                    .bookId(savedBook.getBookId())
                    .imageLink(savedBook.getImageLink())
                    .title(savedBook.getTitle())
                    .author(savedBook.getAuthor())
                    .price(savedBook.getPrice())
                    .amount(savedBook.getAmount())
                    .build();

            Mockito.when(shoppingCartRepository.existsByBookId(bookInCart.getBookId()))
                    .thenReturn(true);
            Mockito.when(shoppingCartRepository.findByBookId(bookInCart.getBookId()))
                    .thenReturn(bookInCart);
            Mockito.when(shoppingCartRepository.save(updatedBook)).thenReturn(savedBook);

            actualResponse = shoppingCartService.updateBookInCart(bookToUpdate.getBookId(), newAmount);

            Assertions.assertEquals(expectedResponse, actualResponse);
        }
    }

    @Test
    public void whenUpdateBookInChartAnd_thenThrowEntityNotFoundException() {
        int[] amounts = IntStream.rangeClosed(1, booksInCart.size()).toArray();
        ShoppingCart bookInCart;

        for (int i = 0; i < booksInCart.size(); i++) {
            bookInCart = booksInCart.get(i);
            int bookId = bookInCart.getBookId() + booksInCart.size();
            int newAmount = amounts[i];
            String expectedResponse = String.format("Book (ID: %d) does not exist!", bookId);

            Mockito.when(shoppingCartRepository.existsByBookId(bookId)).thenReturn(false);

            Exception exception= Assertions.assertThrows(EntityNotFoundException.class, () -> {
                shoppingCartService.updateBookInCart(bookId, newAmount);
            });

            Assertions.assertEquals(expectedResponse, exception.getMessage());
        }
    }
}
