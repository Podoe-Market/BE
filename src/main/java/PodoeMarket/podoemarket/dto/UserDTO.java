package PodoeMarket.podoemarket.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private UUID id;
    private String email;
    private String password;
    private String phoneNumber;
    private String nickname;
    private boolean auth;
    private String accessToken; // jwt 저장공간
    private String refreshToken; // jwt 저장공간
}
