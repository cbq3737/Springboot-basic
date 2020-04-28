package com.springboot.sample;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlHeading1;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(SampleController.class)
public class SampleControllerTest {

    @Autowired
    MockMvc mockMvc;

    //MockMvc대신 WebClient를 주입받아 테스트 할 수 있다.WebClient로 특정 페이지에 요청을 보내고 결과를 받아서 HtmlPage라는 인터페이스를 통해 xml,text등 여러가지로 가져올 수 있다.
//    @Autowired
//    WebClient webClient;

    @Test
    public void hello() throws Exception
    {
            mockMvc.perform(MockMvcRequestBuilders.get("/hello"))//요청
                //.andExpect(status().isOk())//여기서 부터 응답
                //.andExpect(view().name("hello"))
                //.andExpect(model().attribute("name", "beomkyuaa"));
                    .andDo(print())//이 구문은 Spring HATEOAS를 위함.->sampleController
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$._links.self").exists());

//   HtmlPage page= webClient.getPage("/hello");
//   HtmlHeading1 h1 = page.getFirstByXPath("//h1");//본문중 h1태그를 가져옴.
//   assertThat(h1.getTextContent()).isEqualToIgnoringCase("beomkyuaa");

    }
}