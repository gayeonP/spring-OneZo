package org.kakao.kakaoshopping.domain.repository.item;

import org.junit.jupiter.api.Test;
import org.kakao.kakaoshopping.domain.entity.item.Item;
import org.kakao.kakaoshopping.domain.entity.user.User;
import org.kakao.kakaoshopping.domain.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class ItemRepositoryTest {
    @Autowired
    ItemRepository itemRepository;

    @Autowired
    UserService userService;

    @Test
    void initInsert() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(
                new FileInputStream("src/main/resources/best_11st2.txt"), StandardCharsets.UTF_8));
        User user = userService.findUser(2L);
        List<String> list = new ArrayList<>();
        String str = "";
        while ((str = br.readLine()) != null) {
            list.add(str);
        }
        for (String data : list) {
            String[] values = data.split("@");
            Item item = Item.builder()
                    .name(values[0])
                    .price(BigDecimal.valueOf(Double.parseDouble(values[1])))
                    .itemDetail(values[0] + "입니다.")
                    .imagePath(values[2])
                    .stock(10)
                    .seller(user)
                    .build();
            itemRepository.save(item);
        }
        br.close();
    }

    @Test
    void initInsert2() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(
                new FileInputStream("src/main/resources/best_11st2.txt"), StandardCharsets.UTF_8));
        User user = userService.findUser(1L);
        List<String> list = new ArrayList<>();
        String str = "";
        str = br.readLine();
        list.add(str);

        for (String data : list) {
            String[] values = data.split("@");
            Item item = Item.builder()
                    .name(values[0])
                    .price(BigDecimal.valueOf(Double.parseDouble(values[1])))
                    .itemDetail(values[0] + "입니다.")
                    .imagePath(values[2])
                    .stock(10)
                    .seller(user)
                    .build();
            itemRepository.save(item);
        }
        br.close();
    }
}