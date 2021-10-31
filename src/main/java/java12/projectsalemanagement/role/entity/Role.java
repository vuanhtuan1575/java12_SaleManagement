package java12.projectsalemanagement.role.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java12.projectsalemanagement.common.entity.BaseEntity;
import java12.projectsalemanagement.user.enitty.User;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Table(name = "table_role")
public class Role extends BaseEntity {
	@NotNull
	@Size(min = 3, max = 50)
	@Column(unique = true)
	private String name;
	@Column
	private String description;

	// relationship user - role N-N
	@JsonIgnore
	@Builder.Default
	@ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
	private Set<User> users = new HashSet<>();
}
