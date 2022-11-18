package fr.caensup.td3.messagerie;

import static org.hamcrest.CoreMatchers.containsStringIgnoringCase;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
class Td3MessagerieApplicationTests {

  private MockMvc mvc;

  @Autowired
  private WebApplicationContext context;


  @BeforeAll
  public void setup() {
    this.mvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
  }

  @Test
  @WithMockUser(username = "tori.cruickshank@hotmail.com", password = "0000")
  void indexTest() throws Exception {
    this.mvc.perform(get("/")).andExpect(status().isOk()).andExpect(view().name("index"))
        .andExpect(model().attributeExists("vue")).andExpect(model().attributeExists("version"))
        .andExpect(content().string(containsStringIgnoringCase("tori.cruickshank@hotmail.com")));
  }

  @Test
  @WithMockUser(username = "tori.cruickshank@hotmail.com", password = "0000")
  void logoutTest() throws Exception {
    this.mvc.perform(get("/logout")).andExpect(status().isNoContent());
  }

  @Test
  @WithAnonymousUser
  void loginTest() throws Exception {
    this.mvc.perform(get("/login"))
        .andExpect(content().string(containsStringIgnoringCase("Connexion à Messagerie App")));
  }
}
