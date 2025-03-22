package org.saycc.springboot.config;

import org.saycc.springboot.entities.*;
import org.saycc.springboot.entities.enums.OrderStatus;
import org.saycc.springboot.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Instant;
import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository prodructRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    public void run(String... args) throws Exception {

        Category cat1 = new Category(null, "Eletro");
        Category cat2 = new Category(null, "Eletro");
        Category cat3 = new Category(null, "Eletro");

        categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));

        Product p1 = new Product(null, "P1", "ass", 2.0, "d");
        Product p2 = new Product(null, "P2", "pen", 3.0, "d");
        Product p3 = new Product(null, "P3", "tits", 4.0, "d");

        prodructRepository.saveAll(Arrays.asList(p1, p2, p3));

        p1.getCategories().add(cat1);
        p2.getCategories().add(cat2);
        p3.getCategories().add(cat3);

        prodructRepository.saveAll(Arrays.asList(p1, p2, p3));

        User u1 = new User(null, "maria", "maria@gmail.com", "9999", "125");
        User u2 = new User(null, "joao", "joao@gmail.com", "123", "4214124");

        userRepository.saveAll(Arrays.asList(u1,u2));

        Order o1 = new Order(null, u1, OrderStatus.CANCELLED, Instant.now());
        Order o2 = new Order(null, u2, OrderStatus.PAID, Instant.now());
        Order o3 = new Order(null, u1, OrderStatus.DELIVERED, Instant.now());

        orderRepository.saveAll(Arrays.asList(o1,o2,o3));

        OrderItem oi1 = new OrderItem(o1, p1, 2, p1.getPrice());
        OrderItem oi2 = new OrderItem(o1, p3, 1, p3.getPrice());
        OrderItem oi3 = new OrderItem(o2, p3, 2, p3.getPrice());

        orderItemRepository.saveAll(Arrays.asList(oi1,oi2,oi3));


    }
}
