package com.springboot.user;

import org.springframework.web.bind.annotation.*;

//Controller에서는 Dao객체를 참조하여 로직을 작성하지 않고, 해당 요청 시 처리할 로직을 service에게 위임하여 처리한 뒤 적잘한 view를 return만 해준다.
//service요청,view반환 역할
//RestController가 붙으면 public뒤에 @ResponseBody를 생략할 수 있다.
@RestController
public class UserController {
    @GetMapping("/hello!")
    public @ResponseBody String hello()
    {
        return "Hello!";
    }
    @PostMapping ("/users/create")
    public User create(@RequestBody User user)
    {
        return user;
    }
}
