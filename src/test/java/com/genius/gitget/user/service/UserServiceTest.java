package com.genius.gitget.user.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.genius.gitget.security.constants.ProviderInfo;
import com.genius.gitget.user.domain.Role;
import com.genius.gitget.user.domain.User;
import com.genius.gitget.user.dto.SignupRequest;
import com.genius.gitget.user.repository.UserRepository;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@Slf4j
class UserServiceTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Test
    @DisplayName("특정 사용자 가입 테스트")
    public void should_matchValues_when_signupUser() {
        //given
        String email = "test@naver.com";
        saveUnsignedUser();
        SignupRequest signupRequest = SignupRequest.builder()
                .identifier(email)
                .nickname("nickname")
                .information("information")
                .interest(List.of("관심사1", "관심사2"))
                .build();

        //when
        User user = userService.findUserByIdentifier(email);

        Long signupUserId = userService.signup(signupRequest);
        User foundUser = userService.findUserById(signupUserId);
        //then
        assertThat(user.getIdentifier()).isEqualTo(foundUser.getIdentifier());
        assertThat(user.getNickname()).isEqualTo(foundUser.getNickname());
        assertThat(user.getProviderInfo()).isEqualTo(foundUser.getProviderInfo());
        assertThat(user.getInformation()).isEqualTo(foundUser.getInformation());
        assertThat(user.getInterest()).isEqualTo(foundUser.getInterest());
    }


    private void saveUnsignedUser() {
        userRepository.save(User.builder()
                .role(Role.NOT_REGISTERED)
                .providerInfo(ProviderInfo.NAVER)
                .identifier("test@naver.com")
                .build());
    }
}