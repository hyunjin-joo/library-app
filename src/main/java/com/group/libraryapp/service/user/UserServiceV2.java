package com.group.libraryapp.service.user;

import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.domain.user.UserRepository;
import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceV2 {

    private final UserRepository userRepository;

    //생성자를 만들어서 스프링 빈을 주입받는다.
    public UserServiceV2(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    // 아래 있는 함수가 시작될 때 start transaction;을 해준다(트랜잭션을 시작!)
    // 함수가 예외 없이 잘 끝났다면 commit
    // 혹시라도 문제가 있다면 rollback
    @Transactional
    //UserRepository를 가져와서 save라는 메소드를 호출한다.
    public void saveUser(UserCreateRequest request){
        userRepository.save(new User(request.getName(), request.getAge()));

    }

    @Transactional(readOnly = true)
    public List<UserResponse> getUsers(){
        //findAll을 사용 시 자동으로 SQL을 날려서 해당 테이블에 있는
        //모든 데이터를 가져온다. 이렇게 가져온 정보는 ListUser가 된다.
         return userRepository.findAll().stream()
         .map(UserResponse::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void updateUser(UserUpdateRequest request){
        //select * from user where id = ?;
        //Optional<User>
       User user = userRepository.findById(request.getId())
               //옵셔널을 열었을 때 안에 유저가 존재하지 않는 다면
               //orElseThrow 예외를 던져준다.
               .orElseThrow(IllegalArgumentException::new);
        user.updateName(request.getName());
    }

    public void deleteUser(String name){
        //SELECT * FROM user WHERE name = ?
        User user = userRepository.findByName(name).orElseThrow(IllegalArgumentException::new);
      //  if(user == null){
      //      throw new IllegalArgumentException();
      //  }

        userRepository.delete(user);




    }


}

