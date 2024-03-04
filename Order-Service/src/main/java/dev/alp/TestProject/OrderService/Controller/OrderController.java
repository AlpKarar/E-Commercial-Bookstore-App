package dev.alp.TestProject.OrderService.Controller;

import dev.alp.TestProject.OrderService.Dto.Request.CreateOrderRequest;
import dev.alp.TestProject.OrderService.Dto.Response.CreateOrderResponse;
import dev.alp.TestProject.OrderService.Dto.Response.GetOrderResponse;
import dev.alp.TestProject.OrderService.Model.Order;
import dev.alp.TestProject.OrderService.Model.OrderItem;
import dev.alp.TestProject.OrderService.Service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<GetOrderResponse>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable String orderId) {
        GetOrderResponse response;

        try {
            response = orderService.getOrderById(orderId);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<CreateOrderResponse> createOrder(@RequestBody CreateOrderRequest request) {
        return ResponseEntity.ok(orderService.createOrder(request));
    }

    @DeleteMapping
    public void deleteOrderById(@RequestParam(name = "orderId") String orderId) {
        orderService.deleteOrderById(orderId);
    }
}