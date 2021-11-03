package java12.projectsalemanagement.sercurity.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import java12.projectsalemanagement.common.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "table_sessionmanager")
@DynamicUpdate
public class SessionManagerEntity extends BaseEntity {
	
	@Column(name = "username", nullable = false)
	private String userName;

	@Column(name = "token", nullable = false)
	private String token;

	@Column(name = "expiration", nullable = true)
	private int expiration;

	@Column(name = "status", nullable = true)
	private String status;

	@Column(name = "tokentype", nullable = true)
	private String tokenType;

	@Column(name = "refresh_token", nullable = true)
	private String refreshToken;

}
