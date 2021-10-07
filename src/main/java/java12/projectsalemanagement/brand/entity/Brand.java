package java12.projectsalemanagement.brand.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import java12.projectsalemanagement.category.entity.Category;
import java12.projectsalemanagement.common.entity.BaseEntity;
import java12.projectsalemanagement.product.entity.Product;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"categories","products"})
@EqualsAndHashCode(exclude = {"categories","products"}, callSuper = false)
@Entity
@Table(name = "brand")
public class Brand extends BaseEntity {

    @NotNull
    @Column
    private String Name;
    @Column
    private String Description;
    @Column
    private String imageUlr;


    // relation product - brand n - n
    @JsonIgnore
    @Builder.Default
    @ManyToMany(mappedBy = "brands",fetch = FetchType.LAZY)
    private Set<Product> products = new HashSet<>();




    // relation categories - brand N-N
    @JsonIgnore
    @Builder.Default
    @ManyToMany(mappedBy = "brands",fetch = FetchType.LAZY)
    private Set<Category> categories = new HashSet<>();



}
