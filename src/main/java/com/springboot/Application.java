package com.springboot;
//빈 설정: applicationContext.xml
//내부 웹 관련 처리 설정: dispatcher-servlet.xml
//톰캣 구동 관련 설정: web.xml

//우리가 서버에서 어떤 작업을 처리해서 요청이 들어왔을 때, 뷰를 만들어 내는 것이 아니라,웹 브라우저나 클라이언트에서 요청이 들어왔을때, 그것에 대한 리소스가 이미 만들어져있고,그걸 보내주기만 하면 됨.
import org.apache.catalina.connector.Connector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
//index페이지란 애플리케이션을 root로 요청했을 때 페이지, 즉 가장 처음 뜨는 페이지. static에 index에 만들어주면 자동으로 첨 페이지 됨.
//동적페이지로 생성->파비콘(웹페이지 키면 탭에 뜨는 지구본 모양), 탬플릿 엔진(code generation,email등 만들 수 있지만 주로 view를 만드는 데 사용):기본적인 템플릿은 같은데 들어가는 값들만 경우에 따라 달라짐.
//스프링부트가 자동 설정을 지원하는 템플릿엔진 중 하나 -> Thymeleaf->pom에서 의존성 주면 자동 설정 적용되고, 모든 동적 생성 view는 src-main-resource-template에서 찾게됨. -> sample
//index페이지가 없을 때, localhost8080으로 요청 시, 보이는 화면이 기본 error handler가 처리해준 결과임.기본 error handling로직은 BasicErrorController에 들어 있다.->@Mvc예외 처리 방법으로 Error를 테스하기위해 Controller를 만들어줌.->SampleController
//error발생 시 ,status코드값에 따라 다른 웹 페이지를 보여줄 수 있음.static또는 templates에 .error라는 디렉토리를 만들고, 상태코드값.html만들어줌.그래서 만일 index가없는상태의 static.error디렉토리라면 root인 local8080을 호출하면 상태코드html이 호출됨.
//Hypermedia As The Engine Of Application State를 구현하기 위해 편리한 기능들을 제공해주는 tool이며, Rest API를 만들 때 서버가 리소스에대한 정보를 제공할 때, 그 리소스와 연관이 되어있는 "링크 정보"들 까지 같이 제공하고, 클라이언트는 제공이 된 연관된 링크 정보를 바탕으로 리소스에 접근한다.
//CORS:다른 origin(Url,hostname인 localhost등,port)끼리 리소스를 공유할 수 있는 기능을 제공하는 표준,SOP(같은 origin에만 요청을 보낼 수 있음)를 우회하기 위한 표준 기술,기본적으로 SOP가 적용되어있음.즉 rest api를 제공하는 서버인 localhost:8080에서 localhost:18080의 어플리케이션에 호출 할 수 없음.원래 MVC에서 CORS기술을 사용하려면,여러가지 Bean들을 설정해줘야하는데 스프링부트는 자동으로 해줌.->springcorssserver프로젝트로
//내장 데이터 베이스 -> springDatabase프로젝트->JDBC의존성을 안넣어주면 자동으로 인메모리 데이터베이스를 설정해줌.
@SpringBootApplication
@RestController
public class Application {
    @GetMapping("/hello!!")
    public String hello()
    {
        return "hello Spring!";
    }

    public static void main(String[] args)
    {
        SpringApplication.run(Application.class, args);
    }
    @Bean
    public ServletWebServerFactory serverFactory() {
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
        tomcat.addAdditionalTomcatConnectors(createStandardConnector()); // 톰캣에 Connector 추가
        return tomcat;
    }

    private Connector createStandardConnector() {
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setPort(8080); // 포트 설정(https랑 달라야함)
        return connector;
    }
}