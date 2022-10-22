package fr.caensup.td3.messagerie;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@AutoConfigureMockMvc
class Td3MessagerieApplicationTests {

  @Autowired private WebApplicationContext context;

  @Value("server.contextPath")
  private String contextPath;

  @Autowired private MockMvc mvc;

  @Test
  void contextLoads() {}

  @BeforeAll
  public void setUp() {
    this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
  }

  @Test
  void indexTest() throws Exception {
    this.mvc.perform(get(contextPath)).andExpect(content().string("Ajouter une organisation"));
  }
}
