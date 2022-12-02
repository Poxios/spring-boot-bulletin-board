package com.poxios.bulletin.domain.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poxios.bulletin.domain.user.dto.UserSignUpRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WithMockUser
@WebMvcTest(UserController.class)
class UserControllerTest {
    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @DisplayName("Signup Test")
    @Test
    void signUp_Test() throws Exception {
//        given
        var user = UserSignUpRequestDto.builder()
                .name("홍길동")
                .email("test@test.com")
                .password("testword").build();

        given(userService.signUp(user)).willReturn(3L);

        String content = objectMapper.writeValueAsString(user);

        // when
        var res = mockMvc.perform(MockMvcRequestBuilders.post("/users").with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(content)).andExpect(status().isOk()).andReturn().getResponse();



        // then
        Integer.parseInt(res.getContentAsString());

    }
}