package com.springboot.sample;

import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

//@Controller
//public class SampleController {
    
//    @GetMapping("/hello")
//    public String hello(Model model)//view의 이름: hello , model데이터를 받기위해 model을 파라미터로 씀
//    {
//        model.addAttribute("name","beomkyuaa"); //hello라는 view에다가 전달해야할 데이터들은 model에 담을 수 있다.
//        return "hello"; //@RestController가 아닌 일반 Controller이기때문에 리턴 값은 응답의 본문이 아니다. 이게 templates으로 전달 되는듯.
//        }//public String hello 자체는 view를 의미한다. view에다 전달할 데이터는 Model에 담아줘서 전달 해 줄 수 있다.

//    @GetMapping("/hello")
//    public String hello(Model model)
//    {
//        throw new SampleException();//Controller에 hello요청이 들어 왔을 때, Error를 던지게 한다. 그후 이앱에 특화되어있는 error정보를 담고 있는 커스텀 클래스를 만들어 줌.(AppError)
//    }
//    @ExceptionHandler(SampleException.class)//Controller에서 @ExceptionHandler를 사용하여 SampleException이 발생할 때 쓰는 errorhandler를 만들어준다.
//    public @ResponseBody AppError sampleError(SampleException e)//SampleException이 발생할 때 쓰는 error handler
//    {
//        AppError appError = new AppError();
//        appError.setMessage("error.app.key");//메세지 resource에서 문자열을 읽어와서 보여주는 식으로 할 수 도 있음.
//        appError.setReason("I don't know");
//        return appError;
//    }
    
//}

@RestController //rest api 구현
public class SampleController
{
    @GetMapping("/hello")
    public EntityModel<Hello> hello()//링크정보를 추가해주기 위해 HateOAS에 있는 EntityModel을 선언함.(우리가 제공할 리소스의 링크정보)
    {
        Hello hello =new Hello();
        hello.setPrefix("hey, ");
        hello.setName("beomkyu");
        
        EntityModel<Hello> helloEntityModel = new EntityModel<>(hello);
        //SampleController 클래스에서 제공하는 hello라는 메서드에 대한 링크를 따서 이링크를 Self라는 릴레이션으로 만들어서 추가
        helloEntityModel.add(linkTo(methodOn(SampleController.class).hello()).withSelfRel());
        return helloEntityModel;//그리고 test실행->body에 링크와 내용이 추가된것을 확인할 수 있다.서버가 안켜져있어서 링크에 연결은 안되는듯.
    }//이렇게 관련된 링크정보들을 EntityModel에 추가해서 리턴하는 식으로 구현하고, 클라이언트도 그걸사용하는 방식이 Hateoas(Rest API의 정점)이다.
}
