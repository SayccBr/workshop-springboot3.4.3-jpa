package org.saycc.springboot.repositories;

import org.saycc.springboot.entities.Order;
import org.saycc.springboot.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
 * Repositório JPA responsável pela comunicação com o banco de dados.
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
