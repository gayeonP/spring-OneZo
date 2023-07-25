package org.kakao.kakaoshopping.domain.entity.cart;

import jakarta.persistence.*;
import lombok.*;
import org.kakao.kakaoshopping.domain.entity.annotation.CustomCreateDate;
import org.kakao.kakaoshopping.domain.entity.item.Item;
import org.kakao.kakaoshopping.domain.entity.user.User;
import org.kakao.kakaoshopping.domain.enums.OrderStatus;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "CART_TB")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(value = {AuditingEntityListener.class})// 엔티티가 변경되는 것을 감지하는 리스너
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    @Setter
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    @Setter
    private Item item;

    @Column(nullable = false)
    @Setter
    private Integer quantity;

    @Column(columnDefinition = "VARCHAR(1) DEFAULT 'N'", nullable = false)
    @Setter
    private OrderStatus orderStatus;

    @CustomCreateDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime regDate;

    public void editCart(Cart cart) {
        this.quantity = cart.getQuantity();
    }

    @Builder
    public Cart(User user, Item item, Integer quantity, OrderStatus orderStatus) {
        this.user = user;
        this.item = item;
        this.quantity = quantity;
        this.orderStatus = orderStatus;
    }

    @Builder(builderMethodName = "toEdit")
    public Cart(Integer quantity, OrderStatus orderStatus) {
        this.quantity = quantity;
        this.orderStatus = orderStatus;
    }
}