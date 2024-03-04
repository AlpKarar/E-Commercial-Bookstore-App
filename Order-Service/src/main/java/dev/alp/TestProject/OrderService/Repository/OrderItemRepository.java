package dev.alp.TestProject.OrderService.Repository;

import dev.alp.TestProject.OrderService.Model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
