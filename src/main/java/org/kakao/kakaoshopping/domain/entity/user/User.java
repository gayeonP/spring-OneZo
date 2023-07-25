package org.kakao.kakaoshopping.domain.entity.user;

import org.kakao.kakaoshopping.domain.entity.BaseEntity;
import org.kakao.kakaoshopping.domain.entity.embedded.PhoneNumber;
import org.kakao.kakaoshopping.domain.enums.UserType;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "USER_TB")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(columnDefinition = "VARCHAR(20)", nullable = false, unique = true)
	private String userId;

	@Column(columnDefinition = "VARCHAR(50)", nullable = false, unique = true)
	private String email;

	@Column(columnDefinition = "VARCHAR(50)", nullable = false)
	private String password;

	@Column(columnDefinition = "VARCHAR(50)", nullable = false)
	private String name;

	@Column(columnDefinition = "VARCHAR(50)", nullable = false, unique = true)
	private String nickname;

	@Embedded
	private PhoneNumber phoneNumber;

	@Column(columnDefinition = "VARCHAR(20)", nullable = false)
	@Enumerated(EnumType.STRING)
	private UserType userType;
}
