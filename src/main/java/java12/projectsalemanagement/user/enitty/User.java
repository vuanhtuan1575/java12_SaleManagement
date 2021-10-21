package java12.projectsalemanagement.user.enitty;

import java12.projectsalemanagement.cart.entity.Cart;
import java12.projectsalemanagement.common.entity.BaseEntity;
import java12.projectsalemanagement.order.entiy.Order;
import java12.projectsalemanagement.role.entity.Role;
import java12.projectsalemanagement.user.util.UserStatus;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
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
@ToString(exclude = {"roles"})
@EqualsAndHashCode(exclude = {"roles"}, callSuper = false)
@Entity
@Table(name = "table_user")
public class User extends BaseEntity {
    @NotNull
    @Column
    @Size(min = 3, max = 50)
    private String username;

    @NotNull
    @Column
    private String password;

    @Email
    @Column(unique = true)
    private String email;
    @Column
    private String name;
    @Column
    private String phone;


    @NotNull
    @Enumerated(EnumType.STRING)
    private UserStatus status;



    // relationship user-role N-N
    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles=new HashSet<>();


    // relationship user-orders 1-N
    @OneToMany(mappedBy="users")
    private Set<Order> orders = new HashSet<>();


//   // relationship user-carts 1-1
//    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
//    private Cart cart;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @PrimaryKeyJoinColumn
    private Cart cart;
    //helper
    public void addRole(Role role) {
        roles.add(role);
        role.getUsers().add(this);
    }

}
