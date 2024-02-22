package dev.alp.TestProject.ShoppingCart.UnitTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.alp.TestProject.ShoppingCart.Controller.ShoppingCartController;
import dev.alp.TestProject.ShoppingCart.Dto.Request.AddedBookRequest;
import dev.alp.TestProject.ShoppingCart.Dto.Response.AddedBookResponse;
import dev.alp.TestProject.ShoppingCart.Dto.Response.GetAllBooksResponse;
import dev.alp.TestProject.ShoppingCart.Dto.Response.UpdateBookResponse;
import dev.alp.TestProject.ShoppingCart.Model.ShoppingCart;
import dev.alp.TestProject.ShoppingCart.Service.ShoppingCartService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(ShoppingCartController.class)
public class ShoppingCartControllerUnitTest {

    private String HOST_URL = "http://localhost:5007/api/shoppingcart";

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ShoppingCartService shoppingCartService;

    ObjectMapper objectMapper = new ObjectMapper();

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
    public void whenGetAllBooks_thenReturnReturnBooksInCartWithStatusOk() throws Exception {
        GetAllBooksResponse response;
        List<GetAllBooksResponse> allBooksInCart = new ArrayList<>();

        for (ShoppingCart bookInCart : booksInCart) {
            response = GetAllBooksResponse.builder()
                    .bookId(bookInCart.getBookId())
                    .imageLink(bookInCart.getImageLink())
                    .title(bookInCart.getTitle())
                    .author(bookInCart.getAuthor())
                    .price(bookInCart.getPrice())
                    .amount(bookInCart.getAmount())
                    .build();

            allBooksInCart.add(response);
        }

        Mockito.when(shoppingCartService.getAllBooks()).thenReturn(allBooksInCart);

        mockMvc.perform(get(HOST_URL + "/getAll"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().json(objectMapper.writeValueAsString(allBooksInCart)));
    }

    @Test
    public void whenAddBookToCart_thenReturnBookAddedInCartWithStatusCreated() throws Exception {
        AddedBookRequest request;
        AddedBookResponse response;

        for (ShoppingCart bookInCart : booksInCart) {
            request = AddedBookRequest.builder()
                    .bookId(bookInCart.getBookId())
                    .imageLink(bookInCart.getImageLink())
                    .title(bookInCart.getTitle())
                    .author(bookInCart.getAuthor())
                    .price(bookInCart.getPrice())
                    .amount(bookInCart.getAmount())
                    .build();

            response = AddedBookResponse.builder()
                    .bookId(bookInCart.getBookId())
                    .imageLink(bookInCart.getImageLink())
                    .title(bookInCart.getTitle())
                    .author(bookInCart.getAuthor())
                    .price(bookInCart.getPrice())
                    .amount(bookInCart.getAmount())
                    .build();

            Mockito.when(shoppingCartService.addBookToCart(request)).thenReturn(response);

            mockMvc.perform(post(HOST_URL + "/addToCart")
                            .contentType("application/json")
                            .content(objectMapper.writeValueAsString(request))
                    )
                    .andDo(print())
                    .andExpect(content().contentType("application/json"))
                    .andExpect(status().isCreated())
                    .andExpect(content().json(objectMapper.writeValueAsString(response)));
        }
    }

    @Test
    public void whenUpdateBookInCart_thenReturnUpdatedBookWithStatusOk() throws Exception {
        UpdateBookResponse response;

        for (ShoppingCart bookInCart : booksInCart) {
            response = UpdateBookResponse.builder()
                    .bookId(bookInCart.getBookId())
                    .imageLink(bookInCart.getImageLink())
                    .title(bookInCart.getTitle())
                    .author(bookInCart.getAuthor())
                    .price(bookInCart.getPrice())
                    .amount(bookInCart.getAmount())
                    .build();

            Mockito.when(shoppingCartService.updateBookInCart(bookInCart.getBookId(), bookInCart.getAmount()))
                    .thenReturn(response);

            mockMvc.perform(patch(HOST_URL + "/update/{bookId}", bookInCart.getBookId())
                            .param("amount", String.valueOf(bookInCart.getAmount()))
            )
            .andDo(print())
            .andExpect(content().contentType("application/json"))
            .andExpect(status().isOk())
            .andExpect(content().json(objectMapper.writeValueAsString(response)));
        }
    }

    @Test
    public void whenUpdateBookInCartAndBookToUpdateDoesNoExist_thenReturnErrorMessageWithStatusBadRequest() throws Exception {
        int newBookId;
        String responseErrorMessage;

        for (ShoppingCart bookInCart : booksInCart) {
            newBookId = bookInCart.getBookId() + booksInCart.size();
            responseErrorMessage = String.format("Book (ID: %d) does not exist!", newBookId);

            Mockito.when(shoppingCartService.updateBookInCart(newBookId,
                                    bookInCart.getAmount())
                    )
                    .thenThrow(new EntityNotFoundException(responseErrorMessage)
                    );

            mockMvc.perform(patch(HOST_URL + "/update/{bookId}", newBookId)
                            .param("amount", String.valueOf(bookInCart.getAmount()))
                    )
                    .andDo(print())
                    .andExpect(status().isBadRequest())
                    .andExpect(content().string(responseErrorMessage));
        }
    }

    @Test
    public void whenDeleteBookInCart_thenReturnStatusWithOk() throws Exception {
        for (ShoppingCart bookInCart : booksInCart) {
            mockMvc.perform(delete(HOST_URL + "/delete/{bookId}", bookInCart.getBookId()))
                    .andDo(print())
                    .andExpect(status().isOk());
        }
    }
}