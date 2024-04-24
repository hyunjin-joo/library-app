package com.group.libraryapp.dto.user.request;

public class UserCreateRequest {

    private String name;
    private Integer age; //Integer 대문자는 null값을 사용할 수 있음, int은 null을 표현할 수 없음

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }
}
