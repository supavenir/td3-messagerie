package fr.caensup.td3.messagerie;

import static org.hamcrest.CoreMatchers.containsStringIgnoringCase;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class})
class Td3MessagerieApplicationTests {
  @Autowired
  private MockMvc mvc;

  private String contextPath = "/";

  @Test
  void contextLoads() {}

  @Test
  void indexTest() throws Exception {
    this.mvc.perform(get(contextPath))
        .andExpect(content().string(containsStringIgnoringCase("Ajouter une organisation")));
  }
}
