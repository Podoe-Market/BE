package PodoeMarket.podoemarket.repository;

import PodoeMarket.podoemarket.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Boolean existsByEmail(String email);
    UserEntity findByEmail(String email);

    Boolean existsByNickname(String nickname);
}
