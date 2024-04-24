package com.group.libraryapp.domain.user;

import com.group.libraryapp.domain.user.loanhistory.UserLoanHistory;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

    @Id // 이 필드는 id라는 걸 알려주기 위해 사용(primary 키)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // autoincrement 설정시 사용
    private Long id = null; //(Long = BigInt)

    // 이름에 null값 불가, 길이는 20, 아래의 name은 mysql에 있는 name이다.
    // db 컬럼과 user 객체 이름이 같으면 생략 가능
    @Column(nullable = false, length = 20) //name varchar(20)
    private String name;

    private Integer age;

    @OneToMany(mappedBy = "user")
    private List<UserLoanHistory> userLoanHistories = new ArrayList<>();

    protected User(){

    }
    public User(String name, Integer age) {
        if(name == null || name.isBlank()){
            throw new IllegalArgumentException(String.format("잘못된 name(%s)이 들어왔습니다", name));
        }
        this.name = name;
        this.age = age;

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }


    public void updateName(String name){
         this.name = name;
    }

    public void loanBook(String bookName){
        this.userLoanHistories.add(new UserLoanHistory(this, bookName,true));
    }

    public void returnBook(String bookName){
        UserLoanHistory targetHistory = this.userLoanHistories.stream()
                .filter(history-> history.getBookName().equals(bookName))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
        targetHistory.doReturn();
    }
}
