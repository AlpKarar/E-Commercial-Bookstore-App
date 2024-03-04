package dev.alp.TestProject.OrderService.Repository;

import dev.alp.TestProject.OrderService.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
