package org.kakao.kakaoshopping.domain.repository.user;

import org.kakao.kakaoshopping.domain.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUserId(String userLoginId);

	boolean existsByUserId(String userLoginId);

	boolean existsByNickname(String nickname);

	boolean existsByEmail(String email);

}
