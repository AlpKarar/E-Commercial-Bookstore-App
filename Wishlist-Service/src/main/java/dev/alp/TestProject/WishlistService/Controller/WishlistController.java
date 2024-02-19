package dev.alp.TestProject.WishlistService.Controller;

import dev.alp.TestProject.WishlistService.Dto.Request.AddToWishlistRequest;
import dev.alp.TestProject.WishlistService.Dto.Response.AddToWishlistResponse;
import dev.alp.TestProject.WishlistService.Dto.Response.GetFromWishlistResponse;
import dev.alp.TestProject.WishlistService.Service.WishlistService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wishlist")
public class WishlistController {

    private final WishlistService wishlistService;

    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<GetFromWishlistResponse>> getAllBooksInWishlist() {
        return ResponseEntity.ok().body(wishlistService.getAllBooksInWishlist());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addBookToWishlist(@RequestBody AddToWishlistRequest request) {
        AddToWishlistResponse response;

        try {
            response = wishlistService.addBookToWishlist(request);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @DeleteMapping("/remove/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void removeBookFromWishlist(@PathVariable Integer id) {
        wishlistService.removeBookFromWishlist(id);
    }
}
