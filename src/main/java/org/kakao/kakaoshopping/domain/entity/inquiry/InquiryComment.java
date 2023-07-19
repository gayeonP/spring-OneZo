package org.kakao.kakaoshopping.domain.entity.inquiry;

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
@Table(name = "INQUIRY_COMMENT_TB")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class InquiryComment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(columnDefinition = "TEXT DEFAULT ''", nullable = false)
	private String contents;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	@Setter
	private Inquiry inquiry;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	@Setter
	private User user;
}
