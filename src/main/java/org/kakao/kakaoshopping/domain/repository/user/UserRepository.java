package org.kakao.kakaoshopping.domain.repository.user;

import org.kakao.kakaoshopping.domain.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

	public User findByUserId(String userId);
}
