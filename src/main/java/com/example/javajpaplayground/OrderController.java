package com.example.javajpaplayground;

import com.example.javajpaplayground.domain.dto.OrderResponseDto;
import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final HikariDataSource dataSource;

    @GetMapping()
    public ResponseEntity<List<OrderResponseDto>> findAll() throws InterruptedException {
        System.out.println("HIKARI A " + dataSource.getHikariPoolMXBean().getActiveConnections());
        Thread.sleep(7000);

        List<OrderResponseDto> list = orderService.findAll();

        System.out.println("HIKARI D" + dataSource.getHikariPoolMXBean().getActiveConnections());
        Thread.sleep(7000);

        return ResponseEntity.ok(list);
    }

    @GetMapping("/filters")
    public ResponseEntity<List<OrderResponseDto>> findAllFiltered() {
        return ResponseEntity.ok(orderService.findAllFiltered());
    }
}
