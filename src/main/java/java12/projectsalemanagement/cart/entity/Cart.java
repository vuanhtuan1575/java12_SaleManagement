package java12.projectsalemanagement.cart.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java12.projectsalemanagement.common.entity.BaseEntity;
import java12.projectsalemanagement.product.entity.Product;
import java12.projectsalemanagement.user.enitty.User;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"products"})
@EqualsAndHashCode(exclude = {"products"}, callSuper = false)
@Entity
@Table(name = "cart")
public class Cart extends BaseEntity {
//    //user - cart 1-1
//    @OneToOne
//    @JoinColumn(name = "id",referencedColumnName = "user_id") // Liên kết với nhau qua khóa ngoại user_id
//    private User user;

    // relation product-cart N-N
    @JsonIgnore
    @Builder.Default
    @ManyToMany(mappedBy = "carts", fetch = FetchType.LAZY)
    private Set<Product> products = new HashSet<>();

}
