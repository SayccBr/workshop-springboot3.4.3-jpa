package org.saycc.springboot.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.saycc.springboot.entities.enums.OrderStatus;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

/*
 * Classe que representa a entidade Pedido no banco de dados.
 */
@Entity
@Table(name = "tb_order")
public class Order implements Serializable {

    /*
     * Define a chave primária da entidade.
     */
    @Id
    @GeneratedValue // Gera valores automaticamente para a chave primária
    private Long id;

    /*
     * Formata a data para o padrão ISO 8601.
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'z'", timezone = "GMT")
    private Instant createdAt;

    private Integer status;

    /*
     * Relacionamento Muitos-para-Um com a entidade User.
     * Cada pedido pertence a um único cliente.
     */
    @ManyToOne
    @JoinColumn(name = "client_id") // Define a chave estrangeira na tabela "orders"
    private User client;

    /*
     * Relacionamento Um-para-Muitos com OrderItem.
     * Um pedido pode ter vários itens.
     */
    @OneToMany(mappedBy = "orderItemPK.order")
    private Set<OrderItem> items = new HashSet<>();


    /*
     * Relacionamento Um-para-Um com Payment.
     * Um pedido pode ter um pagamento associado.
     * O cascade ALL garante que ao remover um pedido, seu pagamento também será removido.
     */
    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private Payment payment;

    public Order() {
    }

    public Order(Long id, User client, OrderStatus status, Instant createdAt) {
        this.id = id;
        this.client = client;
        setStatus(status);
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public OrderStatus getStatus() {
        return OrderStatus.getOrderStatus(status);
    }

    public void setStatus(OrderStatus status) {
        if(status != null) {
            this.status = status.getCode();
        }
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Set<OrderItem> getItems() {
        return items;
    }

    public Double getTotalPrice() {
        double sum = 0.0;
        for (OrderItem item : items) {
            sum += item.getSubTotalPrice();
        }
        return sum;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;
        return id.equals(order.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
