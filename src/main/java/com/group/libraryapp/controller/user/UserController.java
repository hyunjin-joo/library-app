package com.group.libraryapp.controller.user;

import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import com.group.libraryapp.service.user.UserServiceV2;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //API의 진입지점으로 삼기 위한 첫번째 방법
public class UserController {

    private final UserServiceV2 userService;

    public UserController(UserServiceV2 userService) {
        this.userService = userService;
    }
    // jdbctemplate을 받아서 설정해준다.(생성자)
    @PostMapping("/user") // POST /user로 들어온다
    // void는 아무것도 반환할 게 없을 때 사용
    public void saveUser(@RequestBody UserCreateRequest request) {

        userService.saveUser(request);
    }

    @GetMapping("/user")
    public List<UserResponse> getUsers() {

        return userService.getUsers();
    }

    @PutMapping("/user") //어노테이션을 통해서 put /user 가 호출되면 updateUser가 호출되고,
    public void updateUser(@RequestBody UserUpdateRequest request){
        // 이 함수가 호출될 때 API를 통해 건너온 RequestBody가 UserUpdateRequest로 변형되서 들어온다.
        userService.updateUser(request);
    }

    @DeleteMapping("/user")
    public void deleteUser( @RequestParam String name){

        userService.deleteUser(name);
    }

}