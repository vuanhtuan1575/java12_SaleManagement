package java12.projectsalemanagement.product.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import java12.projectsalemanagement.user.enitty.User;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Data
@NoArgsConstructor
@Entity(name = "table_ProductUser")
public class ProductUser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(updatable = false)
	private Long id;

	@ManyToOne
    @JoinColumn(name = "product_id")
	private Product product;

	@ManyToOne
    @JoinColumn(name = "user_id")
	private User user;

	private int quanty;
}
