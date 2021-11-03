package java12.projectsalemanagement.product.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java12.projectsalemanagement.brand.entity.Brand;
import java12.projectsalemanagement.category.entity.Category;
import java12.projectsalemanagement.common.entity.BaseEntity;
import java12.projectsalemanagement.order.entiy.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
public class Product extends BaseEntity {
	@NotNull
	@Column(unique = true)
	@Size(min = 3, max = 255)
	private String name;

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

	// relationship product - order N-N

	@JsonIgnore
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "product_order", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "order_id"))
	private Set<Order> orders = new HashSet<>();

	// relation product-cart one to many
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	Set<ProductUser> quanty;

	// many to one brand
	@ManyToOne
	@JoinColumn(name = "brand_id", nullable = false)
	private Brand brand;

	// relation product-Category one to many
	@ManyToOne
	@JoinColumn(name = "category_id", nullable = false)
	private Category category;

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
