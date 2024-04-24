package com.group.libraryapp.controller.calculator;

import com.group.libraryapp.dto.calculator.request.CalculatorAddRequest;
import com.group.libraryapp.dto.calculator.request.CalculatorMultiplyRequest;
import org.springframework.web.bind.annotation.*;

@RestController // 꼭 넣어야 함
// CalculatorController을 API의 진입 지점으로 만들어 준다.

public class CalculatorController {

    @GetMapping("/add")
    //GetMapping - HTTP Method 의 GET , /add - API 명세

    public int addTwoNumbers(CalculatorAddRequest request){
        //API가 호출될 때 주어진 쿼리가 CalculatorAddRequest에 있는 객체의 값에 들어가게 된다
        return request.getNumber1() + request.getNumber2();
    }

    @PostMapping("/multiply") // POST /multiply

    //@RequestBody 이 어노테이션이 있어야 POST API에서 정상적으로 HTTP BODY 담겨있는 객체를
    // CalculatorMultiplyRequest 객체로 배달시킬 수 있다
    public int multiplyTwoNumbers(@RequestBody CalculatorMultiplyRequest request){
        return request.getNumber1() * request.getNumber2();

    }


}
