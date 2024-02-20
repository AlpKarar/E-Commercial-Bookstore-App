package dev.alp.TestProject.ShoppingCart.Repository;

import dev.alp.TestProject.ShoppingCart.Model.ShoppingCart;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {

    ShoppingCart findByBookId(Integer bookId);
    boolean existsByBookId(Integer bookId);

    @Transactional
    void deleteByBookId(Integer bookId);
}
