package com.poxios.bulletin.domain.user;


import com.poxios.bulletin.domain.user.dto.UserSignUpRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {
    @Transactional
    public Long signUp(UserSignUpRequestDto requestDto) {
        return 1L;
    }
}
