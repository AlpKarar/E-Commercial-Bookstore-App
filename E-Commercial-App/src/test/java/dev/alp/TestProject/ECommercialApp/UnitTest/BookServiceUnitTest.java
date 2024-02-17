package dev.alp.TestProject.ECommercialApp.UnitTest;

import dev.alp.TestProject.ECommercialApp.Dto.Request.CreateBookRequest;
import dev.alp.TestProject.ECommercialApp.Dto.Request.UpdateBookRequest;
import dev.alp.TestProject.ECommercialApp.Dto.Response.CreateBookResponse;
import dev.alp.TestProject.ECommercialApp.Dto.Response.GetBookResponse;
import dev.alp.TestProject.ECommercialApp.Dto.Response.UpdateBookResponse;
import dev.alp.TestProject.ECommercialApp.Model.Book;
import dev.alp.TestProject.ECommercialApp.Repository.BookRepository;
import dev.alp.TestProject.ECommercialApp.Service.BookService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.AfterEach;
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
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class BookServiceUnitTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    List<Book> books = new ArrayList<>();

    @BeforeEach
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

    @AfterEach
    public void tearDown() {
        Mockito.reset(bookRepository);
    }

    @Test
    public void whenGetAllBooks_thenReturnAllBooks() {
        GetBookResponse response1 = GetBookResponse.builder()
                .id(1)
                .imageLink("http://abc.com/img-book1")
                .title("book1-title")
                .author("book1-author")
                .build();

        GetBookResponse response2 = GetBookResponse.builder()
                .id(2)
                .imageLink("http://abc.com/img-book2")
                .title("book2-title")
                .author("book2-author")
                .build();

        GetBookResponse response3 = GetBookResponse.builder()
                .id(3)
                .imageLink("http://abc.com/img-book3")
                .title("book3-title")
                .author("book3-author")
                .build();

        List<GetBookResponse> responseList = List.of(response1, response2, response3);

        Mockito.when(bookRepository.findAll()).thenReturn(books);

        List<GetBookResponse> actualList = bookService.getAllBooks();

        Assertions.assertNotNull(actualList);

        for (int i = 0; i < responseList.size(); i++) {
            Assertions.assertEquals(responseList.get(i), actualList.get(i));
        }
    }

    @Test
     public void whenGetBookById_thenReturnBook() {
        int index = 0;
        Book book = books.get(index);

        GetBookResponse response = GetBookResponse.builder()
                .id(book.getId())
                .imageLink(book.getImageLink())
                .title(book.getTitle())
                .author(book.getAuthor())
                .build();

        Mockito.when(bookRepository.findById(index + 1)).thenReturn(Optional.of(book));

        GetBookResponse actualResponse = bookService.getBookById(String.valueOf(index + 1));

        Assertions.assertNotNull(actualResponse);
        Assertions.assertEquals(response, actualResponse);
    }

    @Test
    public void whenGetBookByIdAndBookDoesNotExist_thenThrowEntityNotFoundException() {
        int index = 0;
        int id = index + books.size() + 1;

        Mockito.when(bookRepository.findById(id)).thenReturn(Optional.empty());

        Exception exception = Assertions.assertThrows(EntityNotFoundException.class, () -> {
            bookService.getBookById(String.valueOf(id));
        });

        Assertions.assertEquals(String.format("Book %s not found", id), exception.getMessage());
    }

    @Test
    public void whenCreateBook_thenCheckIfRepositorySizeOne() {
        CreateBookRequest request = CreateBookRequest.builder()
                .imageLink("http://abc.com:5005/book1-img")
                .title("book1-title")
                .author("book1-author")
                .build();

        Book newBook = Book.builder()
                .imageLink(request.imageLink())
                .title(request.title())
                .author(request.author())
                .build();

        Book savedBook = Book.builder()
                .id(1)
                .imageLink(request.imageLink())
                .title(request.title())
                .author(request.author())
                .build();

        CreateBookResponse response = CreateBookResponse.builder()
                .id(1)
                .imageLink(request.imageLink())
                .title(request.title())
                .author(request.author())
                .build();

        Mockito.when(bookRepository.save(newBook)).thenReturn(savedBook);

        CreateBookResponse actualResponse = bookService.createBook(request);

        Assertions.assertNotNull(actualResponse);
        Assertions.assertEquals(response, actualResponse);
    }

    @Test
    public void whenUpdateBook_whenReturnUpdateBookResponse() {
        int id;
        Book book;
        UpdateBookRequest request;
        Book updatedBook;
        UpdateBookResponse response;

        for (int i = 0; i < books.size(); i++) {
            id = i + 1;
            book = books.get(i);

            request = UpdateBookRequest.builder()
                    .id(id)
                    .imageLink(id % books.size() == 1 ? "NewImageLink" : "")
                    .title(id % books.size() == 2 ? "NewTitle" : "")
                    .author(id % books.size() == 0 ? "NewAuthor" : "")
                    .build();

            updatedBook = Book.builder()
                    .id(id)
                    .imageLink(!request.imageLink().isBlank() ? "NewImageLink" : book.getImageLink())
                    .title(!request.title().isBlank() ? "NewTitle" : book.getTitle())
                    .author(!request.author().isBlank() ? "NewAuthor" : book.getAuthor())
                    .build();

            response = UpdateBookResponse.builder()
                    .id(updatedBook.getId())
                    .imageLink(updatedBook.getImageLink())
                    .title(updatedBook.getTitle())
                    .author(updatedBook.getAuthor())
                    .build();

            Mockito.when(bookRepository.findById(book.getId())).thenReturn(Optional.of(book));
            Mockito.when(bookRepository.save(book)).thenReturn(updatedBook);

            UpdateBookResponse actualResponse = bookService.updateBook(request);

            Assertions.assertEquals(response, actualResponse);
        }
    }

    @Test
    public void whenUpdateBookAndBookToUpdateDoesNotExist_thenThrowEntityNotFoundException() {
        int id;
        Book book;

        for (int i = 0; i < books.size(); i++) {
            id = i + 1 + books.size();
            book = books.get(i);

            UpdateBookRequest request = UpdateBookRequest.builder()
                    .id(id)
                    .imageLink(id % books.size() == 1 ? "NewImageLink" : "")
                    .title(id % books.size() == 2 ? "NewTitle" : "")
                    .author(id % books.size() == 0 ? "NewAuthor" : "")
                    .build();

            Mockito.when(bookRepository.findById(id)).thenReturn(Optional.empty());

            Exception exception = Assertions.assertThrows(EntityNotFoundException.class, () -> {
                bookService.updateBook(request);
            });

            Assertions.assertEquals(String.format("Book %s not found", id), exception.getMessage());
        }
    }
}