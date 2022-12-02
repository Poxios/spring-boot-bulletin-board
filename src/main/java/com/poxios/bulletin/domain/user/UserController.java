package com.poxios.bulletin.domain.user;

import com.poxios.bulletin.domain.user.dto.UserSignUpRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @PostMapping
    public Long signUp(@RequestBody @Valid UserSignUpRequestDto requestDto) {
        return userService.signUp(requestDto);
    }

//    @PutMapping("/{id}")
//    public Long update(@PathVariable Long id, @RequestBody UserUpdateRequestDto requestDto){
//        return userService.update(id, requestDto);
//    }
//
//    @GetMapping("/{id}")
//    public UserResponseDto findById(@PathVariable Long id){
//        return userService.findById(id);
//    }
//
//    @GetMapping("/")
//    public UsersResponseDto getAll(@PathVariable Long id){
//        return userService.getAll(id);
//    }
//
//    @DeleteMapping("/{id}")
//    public Long delete(@PathVariable Long id){
//        userService.delete(id);2
//        return id;
//    }


}
