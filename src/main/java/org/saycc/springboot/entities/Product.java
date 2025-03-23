package org.saycc.springboot.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_product")
public class Product implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;
    private Double price;
    private String image;

    @ManyToMany
    @JoinTable(name = "tb_product_category", joinColumns = @JoinColumn(name = "product_id"),
    inverseJoinColumns = @JoinColumn(name="cactegory_id"))
    private Set<Category> categories  = new HashSet<Category>();

    //Set não admite repetição do mesmo item, por isso usa-se Set ao invez de List
    @OneToMany(mappedBy = "orderItemPK.product")
    private Set<OrderItem> items = new HashSet<>();

    public Product() {
    }

    public Product(Long id, String name, String description, Double price, String image) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.image = image;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonIgnore
    public Set<Order> getOrders() {
        Set<Order> orders = new HashSet<>();
        for (OrderItem orderItem : items) {
            orders.add(orderItem.getOrder());
        }
        return orders;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;
        return id.equals(product.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
