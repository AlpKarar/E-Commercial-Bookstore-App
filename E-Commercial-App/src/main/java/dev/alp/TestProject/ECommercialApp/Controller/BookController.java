package dev.alp.TestProject.ECommercialApp.Controller;

import dev.alp.TestProject.ECommercialApp.Dto.Request.CreateBookRequest;
import dev.alp.TestProject.ECommercialApp.Dto.Request.UpdateBookRequest;
import dev.alp.TestProject.ECommercialApp.Dto.Response.CreateBookResponse;
import dev.alp.TestProject.ECommercialApp.Dto.Response.GetBookResponse;
import dev.alp.TestProject.ECommercialApp.Dto.Response.UpdateBookResponse;
import dev.alp.TestProject.ECommercialApp.Service.BookService;
import jakarta.persistence.EntityNotFoundException;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/book")
@Slf4j
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<GetBookResponse>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping("/get")
    public ResponseEntity<GetBookResponse> getBookById(@RequestParam("bookId") String id) {
        GetBookResponse response;

        try {
            response = bookService.getBookById(id);
        } catch (EntityNotFoundException err) {
            log.warn(err.getMessage());
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(response);
    }

    @PostMapping("/create")
    public ResponseEntity<CreateBookResponse> createBook(@RequestBody CreateBookRequest request) {
        return new ResponseEntity<>(bookService.createBook(request), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<UpdateBookResponse> updateBook(@RequestBody UpdateBookRequest request) {
        UpdateBookResponse response;

        try {
            response = bookService.updateBook(request);
        } catch (EntityNotFoundException err) {
            log.warn(err.getMessage());
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteBookById(@PathVariable String id) {
        bookService.deleteBookById(id);
    }
}