package java12.projectsalemanagement.category.entity;


import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java12.projectsalemanagement.brand.entity.Brand;
import java12.projectsalemanagement.common.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "category")
public class Category  extends BaseEntity {


    @NotNull
    private String name;
    
    @NotNull
    private String description;
    
    private String imageUlr;
    
    @JsonIgnore
    @ManyToMany(mappedBy = "categorys")
    Set<Brand> brands;
}