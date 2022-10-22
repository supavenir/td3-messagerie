package fr.caensup.td3.messagerie;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
class Td3MessagerieApplicationTests {

  @Autowired private MockMvc mvc;

  @Test
  void contextLoads() {}

  @Test
  void indexTest() throws Exception {
    String path = mvc.getDispatcherServlet().getServletContext().getContextPath();
    this.mvc.perform(get(path)).andExpect(content().string("Ajouter une organisation"));
  }
}
