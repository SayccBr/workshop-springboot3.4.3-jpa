package org.saycc.springboot.services;

import org.saycc.springboot.entities.Order;
import org.saycc.springboot.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/*
 * Serviço responsável pela lógica de negócios relacionada a pedidos.
 */
@Service
public class OrderService {

    @Autowired
    private OrderRepository OrderRepository;

    /*
     * Retorna todos os pedidos do banco de dados.
     */
    public List<Order> findAll() {
        return OrderRepository.findAll();
    }

    /*
     * Busca um pedido pelo ID. Lança exceção caso não seja encontrado.
     */
    public Order findById(Long id) {
        Optional<Order> obj = OrderRepository.findById(id);

        return obj.get();
    }
}
