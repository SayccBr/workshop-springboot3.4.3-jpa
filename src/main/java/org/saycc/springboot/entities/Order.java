package org.saycc.springboot.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.saycc.springboot.entities.enums.OrderStatus;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_order")
public class Order implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'z'", timezone = "GMT")
    private Instant createdAt;

    private Integer status;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private User client;

    @OneToMany(mappedBy = "orderItemPK.order")
    private Set<OrderItem> items = new HashSet<>();

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

    public Set<OrderItem> getItems() {
        return items;
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
