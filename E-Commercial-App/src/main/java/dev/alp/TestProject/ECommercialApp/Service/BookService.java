package dev.alp.TestProject.ECommercialApp.Service;

import dev.alp.TestProject.ECommercialApp.Dto.Request.CreateBookRequest;
import dev.alp.TestProject.ECommercialApp.Dto.Request.UpdateBookRequest;
import dev.alp.TestProject.ECommercialApp.Dto.Response.CreateBookResponse;
import dev.alp.TestProject.ECommercialApp.Dto.Response.GetBookResponse;
import dev.alp.TestProject.ECommercialApp.Dto.Response.UpdateBookResponse;
import dev.alp.TestProject.ECommercialApp.Model.Book;
import dev.alp.TestProject.ECommercialApp.Repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<GetBookResponse> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(book -> GetBookResponse.builder()
                        .id(book.getId())
                        .imageLink(book.getImageLink())
                        .title(book.getTitle())
                        .author(book.getAuthor())
                        .build()
                )
                .toList();
    }

    public GetBookResponse getBookById(String id) throws EntityNotFoundException {
        Optional<Book> book = bookRepository.findById(Integer.parseInt(id));

        if (book.isEmpty()) {
            throw new EntityNotFoundException(String.format("Book %s not found", id));
        }

        return GetBookResponse.builder()
                .id(book.get().getId())
                .imageLink(book.get().getImageLink())
                .title(book.get().getTitle())
                .author(book.get().getAuthor())
                .build();
    }

    public CreateBookResponse createBook(CreateBookRequest request) {
        Book newBook = Book.builder()
                .imageLink(request.imageLink())
                .title(request.title())
                .author(request.author())
                .build();

        Book savedBook = bookRepository.save(newBook);

        return CreateBookResponse.builder()
                .id(savedBook.getId())
                .imageLink(savedBook.getImageLink())
                .title(savedBook.getTitle())
                .author(savedBook.getAuthor())
                .build();
    }

    public UpdateBookResponse updateBook(UpdateBookRequest request) throws EntityNotFoundException {
        Optional<Book> bookToUpdate = bookRepository.findById(request.id());

        if (bookToUpdate.isEmpty()) {
            throw new EntityNotFoundException(String.format("Book %s not found", request.id()));
        }

        Book updatedBook = bookToUpdate.get();

        if (!request.imageLink().isBlank()) {
            updatedBook.setImageLink(request.imageLink());
        }

        if (!request.title().isBlank()) {
            updatedBook.setTitle(request.title());
        }

        if (!request.author().isBlank()) {
            updatedBook.setAuthor(request.author());
        }

        bookRepository.save(updatedBook);

        return UpdateBookResponse.builder()
                .id(updatedBook.getId())
                .imageLink(updatedBook.getImageLink())
                .title(updatedBook.getTitle())
                .author(updatedBook.getAuthor())
                .build();
    }

    public void deleteBookById(String id) {
        bookRepository.deleteById(Integer.parseInt(id));
    }
}
