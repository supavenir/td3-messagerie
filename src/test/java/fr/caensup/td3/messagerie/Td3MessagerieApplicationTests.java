package fr.caensup.td3.messagerie;

import static org.hamcrest.CoreMatchers.containsStringIgnoringCase;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import fr.caensup.td3.messagerie.controllers.OrgaController;

@WebMvcTest(OrgaController.class)
@TestInstance(Lifecycle.PER_CLASS)
class Td3MessagerieApplicationTests {
  @Autowired
  private MockMvc mvc;

  private String contextPath = "/";

  @Autowired
  private WebApplicationContext context;


  @BeforeAll
  public void setup() {
    mvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
  }

  @Test
  @WithAnonymousUser
  void indexTest() throws Exception {

    this.mvc.perform(get(contextPath))
        .andExpect(content().string(containsStringIgnoringCase("Connexion à Messagerie App")));

  }
}
