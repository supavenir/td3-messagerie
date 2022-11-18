package fr.caensup.td3.messagerie;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")

class Td3MessagerieApplicationTests {
  @Autowired
  private MockMvc mvc;

  private String contextPath = "/";

  @Test
  void contextLoads() {}

  @Test
  @WithAnonymousUser
  void indexTest() throws Exception {
    /*
     * this.mvc.perform(get(contextPath))
     * .andExpect(content().string(containsStringIgnoringCase("Connexion à votre compte")));
     */
  }
}
