package org.saycc.springboot.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.saycc.springboot.entities.pk.OrderItemPK;

import java.io.Serializable;

@Entity
@Table(name = "tb_order_item")
public class OrderItem implements Serializable {

    @EmbeddedId
    private OrderItemPK orderItemPK = new OrderItemPK();

    private Integer quantity;
    private Double price;

    public OrderItem() {
    }

    public OrderItem(Order order, Product product, Integer quantity, Double price) {
        orderItemPK.setOrder(order);
        orderItemPK.setProduct(product);

        this.quantity = quantity;
        this.price = price;
    }

    @JsonIgnore
    public Order getOrder() {
        return orderItemPK.getOrder();
    }

    public void setOrder(Order order) {
        orderItemPK.setOrder(order);
    }

    public Product getProduct() {
        return orderItemPK.getProduct();
    }

    public void setProduct(Product product) {
        orderItemPK.setProduct(product);
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        OrderItem orderItem = (OrderItem) o;
        return orderItemPK.equals(orderItem.orderItemPK);
    }

    @Override
    public int hashCode() {
        return orderItemPK.hashCode();
    }
}
