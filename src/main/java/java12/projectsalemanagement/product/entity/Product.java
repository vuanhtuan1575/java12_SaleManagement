package java12.projectsalemanagement.product.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import java12.projectsalemanagement.brand.entity.Brand;
import java12.projectsalemanagement.cart.entity.Cart;
import java12.projectsalemanagement.common.entity.BaseEntity;
import java12.projectsalemanagement.order.entiy.Order;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"brands","orders","carts"})
@EqualsAndHashCode(exclude = { "brands", "orders","carts" }, callSuper = false)
@Entity
@Table(name = "product")
public class Product extends BaseEntity {
    @NotNull
    @Column(unique = true)
    @Size(min = 3, max = 255)
    private String Name;

    @NotNull
    @Column
    private String imageUlr;

    @NotNull
    @Column
    private String description;

    @Column
    private String review;
    @Column
    private double price;
    @Column
    private String trademark;


    // relation product - brand n - n
    @JsonIgnore
    @Builder.Default
    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "product_brand", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "brand_id"))

    private Set<Brand> brands = new HashSet<>();


    // relationship product - order N-N

    @JsonIgnore
    @Builder.Default
    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "product_order", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "order_id"))
    private Set<Order> orders = new HashSet<>();

    // relation product-cart N-N
    @Builder.Default
    @JsonIgnore
    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "product_cart",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "cart_id"))
    private Set<Cart> carts = new HashSet<>();

//
//
//
//
//    // helper - relation product-cart N-N
//    public void addCart(Cart cart) {
//        carts.add(cart);
//        cart.getProducts().add(this);
//    }
//
//    public void removeCart(Cart cart) {
//        carts.add(cart);
//        cart.getProducts().remove(this);
//    }
//
    // helper - relationship product-order N-N
    public void addOrder(Order order) {
        orders.add(order);
        order.getProducts().add(this);
    }

    public void removeOrder(Order order) {
        orders.add(order);
        order.getProducts().remove(this);
    }

}
