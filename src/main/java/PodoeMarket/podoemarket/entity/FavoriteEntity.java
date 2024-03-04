package PodoeMarket.podoemarket.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "favorite")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FavoriteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // 회원 시퀀스 아이디 uuid
    // 작품 시퀀스 아이디 int

    // user : favorite = 1 : N
    @ManyToOne(targetEntity = UserEntity.class)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    // product : favorite = 1 : N
    @ManyToOne(targetEntity = ProductEntity.class)
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product;
}