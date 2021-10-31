package java12.projectsalemanagement.brand.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java12.projectsalemanagement.category.entity.Category;
import java12.projectsalemanagement.common.entity.BaseEntity;
import java12.projectsalemanagement.product.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "brand")
public class Brand extends BaseEntity {

	@NotNull
	private String brandName;

	private String description;

	private String urlImage;

	@ManyToMany
	@JoinTable(name = "brand_category", joinColumns = @JoinColumn(name = "brand_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
	Set<Category> categorys;

	// one to many product
	@JsonIgnore
	@OneToMany(mappedBy = "brand")
	private Set<Product> products;

}
