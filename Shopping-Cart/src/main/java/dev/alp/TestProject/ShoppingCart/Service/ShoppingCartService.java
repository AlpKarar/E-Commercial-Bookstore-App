package dev.alp.TestProject.ShoppingCart.Service;

import dev.alp.TestProject.ShoppingCart.Dto.Request.AddedBookRequest;
import dev.alp.TestProject.ShoppingCart.Dto.Response.AddedBookResponse;
import dev.alp.TestProject.ShoppingCart.Dto.Response.GetAllBooksResponse;
import dev.alp.TestProject.ShoppingCart.Dto.Response.UpdateBookResponse;
import dev.alp.TestProject.ShoppingCart.Model.ShoppingCart;
import dev.alp.TestProject.ShoppingCart.Repository.ShoppingCartRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;

    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
    }

    public List<GetAllBooksResponse> getAllBooks() {
        return shoppingCartRepository.findAll()
                .stream()
                .map(book -> GetAllBooksResponse.builder()
                        .bookId(book.getBookId())
                        .imageLink(book.getImageLink())
                        .author(book.getAuthor())
                        .title(book.getTitle())
                        .price(book.getPrice())
                        .amount(book.getAmount())
                        .build()
                ).toList();
    }

    public AddedBookResponse addBookToCart(AddedBookRequest request) {
        ShoppingCart savedBook;

        if (shoppingCartRepository.existsByBookId(request.bookId())) {
            ShoppingCart bookInCart = shoppingCartRepository.findByBookId(request.bookId());
            bookInCart.setAmount(bookInCart.getAmount() + request.amount());
            savedBook = shoppingCartRepository.save(bookInCart);
        } else {
            savedBook = shoppingCartRepository.save(ShoppingCart.builder()
                    .bookId(request.bookId())
                    .imageLink(request.imageLink())
                    .title(request.title())
                    .author(request.author())
                    .price(request.price())
                    .amount(request.amount())
                    .build()
            );
        }

        return AddedBookResponse.builder()
                .bookId(savedBook.getBookId())
                .imageLink(savedBook.getImageLink())
                .title(savedBook.getTitle())
                .author(savedBook.getAuthor())
                .price(savedBook.getPrice())
                .amount(savedBook.getAmount())
                .build();
    }

    public UpdateBookResponse updateBookInCart(Integer bookId, Integer amount)
            throws EntityNotFoundException {
        if (!shoppingCartRepository.existsByBookId(bookId)) {
            throw new EntityNotFoundException(String.format("Book (ID: %d) does not exist!", bookId));
        }

        ShoppingCart bookToUpdate = shoppingCartRepository.findByBookId(bookId);

        bookToUpdate.setAmount(amount);
        bookToUpdate = shoppingCartRepository.save(bookToUpdate);

        return UpdateBookResponse.builder()
                .bookId(bookToUpdate.getBookId())
                .imageLink(bookToUpdate.getImageLink())
                .title(bookToUpdate.getTitle())
                .author(bookToUpdate.getAuthor())
                .price(bookToUpdate.getPrice())
                .amount(bookToUpdate.getAmount())
                .build();
    }

    public void deleteBookInCart(Integer bookId) {
        shoppingCartRepository.deleteByBookId(bookId);
    }
}
