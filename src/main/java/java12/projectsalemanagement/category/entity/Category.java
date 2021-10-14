package java12.projectsalemanagement.category.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import java12.projectsalemanagement.brand.entity.Brand;
import java12.projectsalemanagement.common.entity.BaseEntity;
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
@ToString(exclude = {"brands"})
@EqualsAndHashCode(exclude = {"brands"}, callSuper = false)
@Entity
@Table(name = "category")
public class Category  extends BaseEntity {


    @NotNull
    @Column
    private String name;
    @NotNull
    @Column
    private String description;

    @Column
    private String imageUlr;

    // relation brand - category N-N
    @Builder.Default
    @JsonIgnore
    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "brand_categories",
            joinColumns = @JoinColumn(name = "categories_id"),
            inverseJoinColumns = @JoinColumn(name = "brand_id"))
    private Set<Brand> brands= new HashSet<>();


}
