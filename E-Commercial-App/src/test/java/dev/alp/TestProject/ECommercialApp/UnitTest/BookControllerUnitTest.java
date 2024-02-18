package dev.alp.TestProject.ECommercialApp.UnitTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.alp.TestProject.ECommercialApp.Controller.BookController;
import dev.alp.TestProject.ECommercialApp.Dto.Request.CreateBookRequest;
import dev.alp.TestProject.ECommercialApp.Dto.Request.UpdateBookRequest;
import dev.alp.TestProject.ECommercialApp.Dto.Response.CreateBookResponse;
import dev.alp.TestProject.ECommercialApp.Dto.Response.GetBookResponse;
import dev.alp.TestProject.ECommercialApp.Dto.Response.UpdateBookResponse;
import dev.alp.TestProject.ECommercialApp.Model.Book;
import dev.alp.TestProject.ECommercialApp.Service.BookService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BookController.class)
public class BookControllerUnitTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BookService bookService;

    ObjectMapper objectMapper = new ObjectMapper();

    List<Book> books = new ArrayList<>();

    @Before
    public void setup() {
        Book book1 = Book.builder()
                .id(1)
                .imageLink("http://abc.com/img-book1")
                .title("book1-title")
                .author("book1-author")
                .build();

        Book book2 = Book.builder()
                .id(2)
                .imageLink("http://abc.com/img-book2")
                .title("book2-title")
                .author("book2-author")
                .build();

        Book book3 = Book.builder()
                .id(3)
                .imageLink("http://abc.com/img-book3")
                .title("book3-title")
                .author("book3-author")
                .build();

        books.addAll(List.of(book1, book2, book3));
    }

    @Test
    public void whenGetAllBook_thenReturnBookJsonArray() throws Exception {
        List<GetBookResponse> response = books
                .stream()
                .map(book -> GetBookResponse.builder()
                    .id(book.getId())
                    .imageLink(book.getImageLink())
                    .title(book.getTitle())
                    .author(book.getAuthor())
                    .build()
                )
                .toList();

        Mockito.when(bookService.getAllBooks()).thenReturn(response);

        mvc.perform(get("http://localhost:5005/api/book/getAll"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(response)));
    }

    @Test
    public void whenGetBookByIdAndBookExists_thenReturnBook() throws Exception {
        List<GetBookResponse> response = books.stream().map(book -> GetBookResponse.builder()
                .id(book.getId())
                .imageLink(book.getImageLink())
                .title(book.getTitle())
                .author(book.getAuthor())
                .build()).toList();

        for (int i=0;i<books.size();i++) {
            Mockito.when(bookService.getBookById(String.valueOf(i + 1))).thenReturn(response.get(i));

            mvc.perform(get("http://localhost:5005/api/book/get")
                    .param("bookId", String.valueOf(i + 1))
            )
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().json(objectMapper.writeValueAsString(response.get(i))));
        }
    }

    @Test
    public void whenGetBookByIdAndBookDoesNotExist_thenThrowEntityNotFoundExceptionAndReturnStatusNotFound()
            throws Exception {
        for (int i=0;i< books.size();i++) {
            Mockito.when(bookService.getBookById(String.valueOf(i + 4))).thenThrow(EntityNotFoundException.class);

            mvc.perform(get("http://localhost:5005/api/book/get")
                            .param("bookId", String.valueOf(i + 4))
                    )
                    .andDo(print())
                    .andExpect(status().isNotFound());
        }
    }

    @Test
    public void whenCreateBook_thenReturnSavedBookWithStatusCreated() throws Exception {
        CreateBookRequest request = CreateBookRequest.builder()
                .imageLink("http://abc.com/img-4")
                .title("book4-title")
                .author("book4-author")
                .build();

        CreateBookResponse response = CreateBookResponse.builder()
                .id(1)
                .imageLink(request.imageLink())
                .title(request.title())
                .author(request.author())
                .build();

        Mockito.when(bookService.createBook(request)).thenReturn(response);

        mvc.perform(post("http://localhost:5005/api/book/create")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request))
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().json(objectMapper.writeValueAsString(response)));
    }

    @Test
    public void whenUpdateBook_thenReturnUpdatedBook() throws Exception {
        UpdateBookRequest request;
        UpdateBookResponse response;
        int index;

        for (int i=0; i<books.size(); i++) {
            index = i + 1;

            request = UpdateBookRequest.builder()
                    .id(index)
                    .imageLink(index % books.size() == 1 ? String.format("book%s-imageLinkNew", index) : "")
                    .title(index % books.size() == 2 ? String.format("book%s-titleNew", index) : "")
                    .author(index % books.size() == 0 ? String.format("book%s-authorNew", index) : "")
                    .build();

            response = UpdateBookResponse.builder()
                    .id(index)
                    .imageLink(!request.imageLink().isEmpty() ? request.imageLink() : books.get(i).getImageLink())
                    .title(!request.title().isEmpty() ? request.title() : books.get(i).getTitle())
                    .author(!request.author().isEmpty() ? request.author() : books.get(i).getAuthor())
                    .build();

            Mockito.when(bookService.updateBook(request)).thenReturn(response);

            mvc.perform(put("http://localhost:5005/api/book/update")
                            .contentType("application/json")
                            .content(objectMapper.writeValueAsBytes(request))
                    )
                    .andDo(print())
                    .andExpect(content().json(objectMapper.writeValueAsString(response)));
        }
    }

    @Test
    public void whenUpdateBookAndBookDoesNotExist_thenThrowEntityNotFoundExceptionAndReturnStatusNotFound() throws Exception {
        UpdateBookRequest request;
        int index;

        for (int i=books.size(); i < 2 * books.size(); i++) {
            index = i + 1;

            request = UpdateBookRequest.builder()
                    .id(index)
                    .imageLink(index % books.size() == 1 ? String.format("book%s-imageLinkNew", index) : "")
                    .title(index % books.size() == 2 ? String.format("book%s-titleNew", index) : "")
                    .author(index % books.size() == 0 ? String.format("book%s-authorNew", index) : "")
                    .build();

            Mockito.when(bookService.updateBook(request)).thenThrow(EntityNotFoundException.class);

            mvc.perform(put("http://localhost:5005/api/book/update")
                    .contentType("application/json")
                    .content(objectMapper.writeValueAsBytes(request))
            )
            .andDo(print())
            .andExpect(status().isNotFound());
        }
    }

    @Test
    public void whenDeleteBookById_thenReturnStatusOk() throws Exception {
        for (int i=0; i < books.size(); i++) {
            mvc.perform(delete("http://localhost:5005/api/book/delete/{id}", String.valueOf(i + 1)))
                    .andDo(print())
                    .andExpect(status().isOk());
        }
    }

}