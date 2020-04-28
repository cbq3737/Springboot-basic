package com.springboot;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)//RunWith가 없다고 에러떳는데 아래 PROBLEMS에서 의존성 더해줄 수 있다.
@SpringBootTest
class ApplicationTest {
    @Autowired
    Environment environment;

    @Test
    void contextLoads() {
        //assertThat(environment.getProperty("my.name")).isEqualTo("[prod]beomkyu");//실행할때나 jar파일을 만들때 my.name의 값과 같지않으면 오류가 뜸.
    }
}