package fr.sipios.springmeetup.integration;

import fr.sipios.springmeetup.config.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Sql("classpath:db/insert_books.sql")
public class GetAllBooksIT extends IntegrationTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  void should_retrieve_all_books() throws Exception {
    mockMvc.perform(get("/books"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.books").isArray())
        .andExpect(jsonPath("$.books[0].title").value("Java"))
        .andExpect(jsonPath("$.books[1].title").value("Kotlin"))
        .andExpect(jsonPath("$.books[2].title").value("Scala"));
  }
}
