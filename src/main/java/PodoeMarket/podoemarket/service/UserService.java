package PodoeMarket.podoemarket.service;

import PodoeMarket.podoemarket.entity.UserEntity;
import PodoeMarket.podoemarket.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class UserService {
    private final UserRepository repo;

    public UserEntity create(final UserEntity userEntity) {
        final String email = userEntity.getEmail();
        final String password = userEntity.getPassword();
        final String nickname = userEntity.getNickname();

        // user 정보 확인 - 필드 하나라도 비어있을 경우 확인
        if (userEntity == null) {
            throw new RuntimeException("Invalid arguments");
        }

        // 이메일
        if (email == null || email.isBlank()) {
            throw new RuntimeException("UserId is invalid arguments");
        }

        if (repo.existsByEmail(email)) {
            log.warn("email already exists {}", email);
            throw new RuntimeException("UserId already exists");
        }

        // 비밀번호
        if (password == null) {
            log.info(password);
            throw new RuntimeException("Password is invalid arguments");
        }

        // 닉네임
        if (nickname == null || nickname.isBlank()) {
            throw new RuntimeException("Nickname is invalid arguments");
        }

        if(repo.existsByNickname(nickname)) {
            log.warn("nickname already exists {}", nickname);
            throw new RuntimeException("Nickname already exists");
        }

        return repo.save(userEntity);
    }

    public UserEntity getByCredentials(final String email, final String password, final PasswordEncoder encoder){
        log.info("find user by email");

        try {
            final UserEntity originalUser = repo.findByEmail(email);
            log.info("original User: {}", originalUser);

            if (originalUser != null && encoder.matches(password, originalUser.getPassword())) {
                log.info("same Password");
                return originalUser;
            } else if (originalUser == null) {
                log.info("wrong email");

                // 로그인 실패 시, 실패 이유 전달을 위한 메세지 작성
                UserEntity user = new UserEntity();
                user.setNickname("wrong email");

                return user;
            } else if (!encoder.matches(password, originalUser.getPassword())) {
                log.info("wrong password");

                UserEntity user = new UserEntity();
                user.setNickname("wrong password");

                return user;
            } else {
                log.info("signin error");
                return null;
            }
        }catch (Exception e){
            log.error("UserService.getByCredentials 메소드 중 예외 발생", e);
            return null;
        }
    }
}
