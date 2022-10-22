package fr.caensup.td3.messagerie;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class Td3MessagerieApplicationTests {

  @Value("server.contextPath")
  private String contextPath;

  @Autowired private MockMvc mvc;

  @Test
  void contextLoads() {}

  @Test
  void indexTest() throws Exception {
    this.mvc.perform(get(contextPath)).andExpect(content().string("Ajouter une organisation"));
  }
}
