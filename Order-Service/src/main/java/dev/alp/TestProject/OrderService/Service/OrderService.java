package dev.alp.TestProject.OrderService.Service;

import dev.alp.TestProject.OrderService.Dto.Request.CreateOrderRequest;
import dev.alp.TestProject.OrderService.Dto.Response.CreateOrderResponse;
import dev.alp.TestProject.OrderService.Dto.Response.GetOrderResponse;
import dev.alp.TestProject.OrderService.Dto.Response.OrderItemResponseDto;
import dev.alp.TestProject.OrderService.Model.Order;
import dev.alp.TestProject.OrderService.Model.OrderItem;
import dev.alp.TestProject.OrderService.Repository.OrderItemRepository;
import dev.alp.TestProject.OrderService.Repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    public OrderService(OrderRepository orderRepository, OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }

    public List<GetOrderResponse> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(order -> GetOrderResponse.builder()
                        .accountId(order.getAccountId())
                        .total(order.getTotal())
                        .orderItems(order.getOrderItems()
                                .stream()
                                .map(orderItemResponse -> OrderItemResponseDto.builder()
                                        .bookId(orderItemResponse.getBookId())
                                        .imageLink(orderItemResponse.getImageLink())
                                        .title(orderItemResponse.getTitle())
                                        .author(orderItemResponse.getAuthor())
                                        .price(orderItemResponse.getPrice())
                                        .amount(orderItemResponse.getAmount())
                                        .build()
                                ).toList()
                        )
                        .build()
                )
                .toList();
    }

    public GetOrderResponse getOrderById(String orderId) throws EntityNotFoundException {
        Optional<Order> order = orderRepository.findById(Long.parseLong(orderId));
        if (order.isEmpty()) {
            throw new EntityNotFoundException(String.format("Order with ID %s not found", orderId));
        }

        return GetOrderResponse.builder()
                .accountId(order.get().getAccountId())
                .total(order.get().getTotal())
                .orderItems(order.get().getOrderItems()
                        .stream()
                        .map(orderItem -> OrderItemResponseDto.builder()
                                .bookId(orderItem.getBookId())
                                .imageLink(orderItem.getImageLink())
                                .title(orderItem.getTitle())
                                .author(orderItem.getAuthor())
                                .price(orderItem.getPrice())
                                .amount(orderItem.getAmount())
                                .build()
                        )
                        .toList()
                )
                .build();
    }

    public CreateOrderResponse createOrder(CreateOrderRequest request) {
        List<OrderItem> orderItems = request.orderItems()
                .stream()
                .map(orderItemRequestDto -> OrderItem.builder()
                        .bookId(orderItemRequestDto.bookId())
                        .imageLink(orderItemRequestDto.imageLink())
                        .title(orderItemRequestDto.title())
                        .author(orderItemRequestDto.author())
                        .price(orderItemRequestDto.price())
                        .amount(orderItemRequestDto.amount())
                        .build())
                .toList();

        List<OrderItem> savedOrderItems = orderItemRepository.saveAll(orderItems);

        Order order = Order.builder()
                .accountId(request.accountId())
                .total(request.total())
                .orderDate(LocalDateTime.now())
                .orderItems(savedOrderItems)
                .build();

        Order savedOrder = orderRepository.save(order);

        return CreateOrderResponse.builder()
                .accountId(savedOrder.getAccountId())
                .total(savedOrder.getTotal())
                .orderItems(savedOrderItems.stream()
                        .map(orderItem -> OrderItemResponseDto.builder()
                                .bookId(orderItem.getBookId())
                                .imageLink(orderItem.getImageLink())
                                .title(orderItem.getTitle())
                                .author(orderItem.getAuthor())
                                .price(orderItem.getPrice())
                                .amount(orderItem.getAmount())
                                .build()
                        )
                        .toList())
                .build();
    }

    public void deleteOrderById(String orderId) {
        orderRepository.deleteById(Long.parseLong(orderId));
    }
}
