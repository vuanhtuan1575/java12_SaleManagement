package java12.projectsalemanagement.order.entiy;

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
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"products"})
@EqualsAndHashCode(exclude = {"products"}, callSuper = false)
@Entity
@Table(name = "talbe_order")
public class Order extends BaseEntity {
@Column
    private float price;
@Column
    private int quantity;

    // relationship product - order N-N
    @JsonIgnore
    @Builder.Default
    @ManyToMany(mappedBy = "orders",fetch = FetchType.LAZY)
    private Set<Product> products = new HashSet<>();

    // relationship user - order 1-N
    @ManyToOne
    @JoinColumn(name = "user_id", nullable=false)
    private User users;
}
