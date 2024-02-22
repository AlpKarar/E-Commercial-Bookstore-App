package dev.alp.TestProject.ShoppingCart.Controller;

import dev.alp.TestProject.ShoppingCart.Dto.Request.AddedBookRequest;
import dev.alp.TestProject.ShoppingCart.Dto.Response.AddedBookResponse;
import dev.alp.TestProject.ShoppingCart.Dto.Response.GetAllBooksResponse;
import dev.alp.TestProject.ShoppingCart.Dto.Response.UpdateBookResponse;
import dev.alp.TestProject.ShoppingCart.Service.ShoppingCartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shoppingcart")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<GetAllBooksResponse>> getAllBooks() {
        return ResponseEntity.ok(shoppingCartService.getAllBooks());
    }

    @PostMapping("/addToCart")
    public ResponseEntity<AddedBookResponse> addBookToCart(@RequestBody AddedBookRequest request) {
        return new ResponseEntity<>(shoppingCartService.addBookToCart(request), HttpStatus.CREATED);
    }

    @PatchMapping("/update/{bookId}")
    public ResponseEntity<?> updateBookInCart(@PathVariable Integer bookId, @RequestParam("amount") Integer amount) {
        UpdateBookResponse response;

        try {
            response = shoppingCartService.updateBookInCart(bookId, amount);
        } catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{bookId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteBookInCart(@PathVariable String bookId) {
        shoppingCartService.deleteBookInCart(Integer.valueOf(bookId));
    }
}
