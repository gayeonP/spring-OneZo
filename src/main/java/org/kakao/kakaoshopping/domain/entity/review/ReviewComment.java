package org.kakao.kakaoshopping.domain.entity.review;

import org.kakao.kakaoshopping.domain.entity.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "REVIEW_COMMENT_TB")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReviewComment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(columnDefinition = "TEXT DEFAULT ''", nullable = false)
	private String contents;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	@Setter
	private Review review;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	@Setter
	private User user;
}
