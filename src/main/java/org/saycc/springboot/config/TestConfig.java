package org.saycc.springboot.config;

import org.saycc.springboot.entities.Order;
import org.saycc.springboot.entities.User;
import org.saycc.springboot.repositories.OrderRepository;
import org.saycc.springboot.repositories.UserRepository;
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

    @Override
    public void run(String... args) throws Exception {
        User u1 = new User(null, "maria", "maria@gmail.com", "9999", "125");
        User u2 = new User(null, "joao", "joao@gmail.com", "123", "4214124");

        userRepository.saveAll(Arrays.asList(u1,u2));

        Order o1 = new Order(null, u1, Instant.now());
        Order o2 = new Order(null, u2, Instant.now());
        Order o3 = new Order(null, u1, Instant.now());

        orderRepository.saveAll(Arrays.asList(o1,o2,o3));
    }
}
