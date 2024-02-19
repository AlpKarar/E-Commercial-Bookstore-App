package dev.alp.TestProject.WishlistService.UnitTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.alp.TestProject.WishlistService.Controller.WishlistController;
import dev.alp.TestProject.WishlistService.Dto.Request.AddToWishlistRequest;
import dev.alp.TestProject.WishlistService.Dto.Response.AddToWishlistResponse;
import dev.alp.TestProject.WishlistService.Dto.Response.GetFromWishlistResponse;
import dev.alp.TestProject.WishlistService.Model.Wishlist;
import dev.alp.TestProject.WishlistService.Service.WishlistService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(WishlistController.class)
public class WishlistControllerUnitTest {

    @MockBean
    public WishlistService wishlistService;

    @Autowired
    MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    List<Wishlist> booksInWishlist = new ArrayList<>();

    private String HOST_URL = "http://localhost:5006/api/wishlist";

    @BeforeEach
    public void setup() {
        Wishlist book1 = Wishlist.builder()
                .id("65d25ef60344d82fd3ee10a2")
                .bookId(1)
                .imageLink("http://abc.com/img-1")
                .title("title-1")
                .author("author-1")
                .build();

        Wishlist book2 = Wishlist.builder()
                .id("65d267ad38f9bd6e239a70d5")
                .bookId(2)
                .imageLink("http://abc.com/img-2")
                .title("title-2")
                .author("author-2")
                .build();

        Wishlist book3 = Wishlist.builder()
                .id("65d25ef60344d82fd3gjwsu")
                .bookId(3)
                .imageLink("http://abc.com/img-3")
                .title("title-3")
                .author("author-3")
                .build();

        booksInWishlist.addAll(List.of(book1, book2, book3));
    }

    @Test
    public void whenGetAllBooksInWishlist_thenReturnBooksInWishlistWithStatusOk() throws Exception {
        Wishlist curBookInWishlist;
        GetFromWishlistResponse curResponse;
        List<GetFromWishlistResponse> expectedResponse = new ArrayList<>();


        for (Wishlist wishlist : booksInWishlist) {
            curBookInWishlist = wishlist;

            curResponse = GetFromWishlistResponse.builder()
                    .id(curBookInWishlist.getId())
                    .bookId(curBookInWishlist.getBookId())
                    .imageLink(curBookInWishlist.getImageLink())
                    .title(curBookInWishlist.getTitle())
                    .author(curBookInWishlist.getAuthor())
                    .build();

            expectedResponse.add(curResponse);
        }

        Mockito.when(wishlistService.getAllBooksInWishlist()).thenReturn(expectedResponse);

        mockMvc.perform(get(HOST_URL + "/all"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(expectedResponse)));
    }

    @Test
    public void whenAddBookToWishlist_thenReturnAddedBookWithStatusCreated() throws Exception {
        AddToWishlistRequest request;
        AddToWishlistResponse response;

        for (Wishlist book : booksInWishlist) {
            request = AddToWishlistRequest.builder()
                    .bookId(book.getBookId())
                    .imageLink(book.getImageLink())
                    .title(book.getTitle())
                    .author(book.getAuthor())
                    .build();

            response = AddToWishlistResponse.builder()
                    .bookId(request.bookId())
                    .imageLink(request.imageLink())
                    .title(request.title())
                    .author(request.author())
                    .build();

            Mockito.when(wishlistService.addBookToWishlist(request)).thenReturn(response);

            mockMvc.perform(post(HOST_URL + "/add")
                    .contentType("application/json")
                    .content(objectMapper.writeValueAsString(request))
            )
            .andDo(print())
            .andExpect(status().isCreated())
            .andExpect(content().contentType("application/json"))
            .andExpect(content().json(objectMapper.writeValueAsString(response)));
        }
    }

    @Test
    public void whenAddBookToWishlistAndBookDuplicated_thenReturnErrorMessageWithStatusBadRequest() throws Exception {
        AddToWishlistRequest request;

        for (Wishlist book : booksInWishlist) {
            request = AddToWishlistRequest.builder()
                    .bookId(book.getBookId())
                    .imageLink(book.getImageLink())
                    .title(book.getTitle())
                    .author(book.getAuthor())
                    .build();

            String expectedResponse = String.format("Duplicate Key Error - Book ID: %s", request.bookId());

            Mockito.when(wishlistService.addBookToWishlist(request)).thenThrow(new Exception(expectedResponse));

            mockMvc.perform(post(HOST_URL + "/add")
                            .contentType("application/json")
                            .content(objectMapper.writeValueAsString(request))
                    )
                    .andDo(print())
                    .andExpect(status().isBadRequest())
                    .andExpect(content().string(String.format("Duplicate Key Error - Book ID: %s", request.bookId())));
        }
    }

    @Test
    public void whenRemoveBookFromWishlist_thenReturnStatusOk() throws Exception {
        int bookId;

        for (Wishlist book : booksInWishlist) {
            bookId = book.getBookId();

            mockMvc.perform(delete(HOST_URL + "/remove/{id}", bookId))
                    .andDo(print())
                    .andExpect(status().isOk());
        }
    }
}
