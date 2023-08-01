package org.kakao.kakaoshopping.domain.entity;

import java.time.LocalDateTime;

import org.kakao.kakaoshopping.domain.entity.annotation.CustomCreateDate;
import org.kakao.kakaoshopping.domain.entity.annotation.CustomLastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

@MappedSuperclass // @MappedSuperclass를 선언하여 DB의 테이블로 만들지 않도록 설정
@EntityListeners(value = {AuditingEntityListener.class})// 엔티티가 변경되는 것을 감지하는 리스너
@Getter
public abstract class BaseEntity {

	@CustomCreateDate
	@Column(updatable = false, nullable = false)
	protected LocalDateTime regDate;

	@CustomLastModifiedDate
	@Column
	protected LocalDateTime modDate;
}
