package org.kakao.kakaoshopping.domain.entity.support;

import java.util.ArrayList;
import java.util.List;

import org.kakao.kakaoshopping.domain.entity.BaseEntity;
import org.kakao.kakaoshopping.domain.entity.user.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "SUPPORT_TB")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Support extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	@Setter
	private User user;

	@OneToMany(mappedBy = "support", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<SupportComment> comments = new ArrayList<>();

	@Column(columnDefinition = "VARCHAR(100) DEFAULT ''", nullable = false)
	private String title;

	@Column(columnDefinition = "TEXT DEFAULT ''", nullable = false)
	private String contents;
}
