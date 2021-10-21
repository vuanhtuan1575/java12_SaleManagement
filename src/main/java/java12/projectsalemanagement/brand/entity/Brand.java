package java12.projectsalemanagement.brand.entity;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java12.projectsalemanagement.category.entity.Category;
import java12.projectsalemanagement.common.entity.BaseEntity;
import java12.projectsalemanagement.product.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
    private String brandName;
   
    @Column
    private String description;
    @Column
    private String urlImage;


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
