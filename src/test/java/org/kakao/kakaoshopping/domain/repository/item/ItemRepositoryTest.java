package org.kakao.kakaoshopping.domain.repository.item;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kakao.kakaoshopping.domain.entity.embedded.PhoneNumber;
import org.kakao.kakaoshopping.domain.entity.item.Item;
import org.kakao.kakaoshopping.domain.entity.user.User;
import org.kakao.kakaoshopping.domain.enums.UserType;
import org.kakao.kakaoshopping.domain.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.stream.IntStream;

@SpringBootTest
class ItemRepositoryTest {
    @Autowired
    ItemRepository itemRepository;

    @Autowired
    UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository.save(User.builder()
                .email("email1")
                .name("name1")
                .nickname("nickname1")
                .password("password1")
                .phoneNumber(PhoneNumber.builder().headNumber("010")
                        .middleNumber("1234")
                        .tailNumber("5678").build())
                .userType(UserType.USER)
                .build());
    }

    @Test
    void insertDummyData() {
        User user = userRepository.findById(1L).orElse(null);
        IntStream.range(1, 501)
                .forEach(i -> {
                    Item item = Item.builder()
                            .imagePath("imagePath" + i)
                            .itemDetail("imageDetail" + i)
                            .name("name" + i)
                            .price(BigDecimal.valueOf(1010 + i))
                            .stock(10 + i)
                            .seller(user)
                            .build();
                    itemRepository.save(item);
                });
    }

}